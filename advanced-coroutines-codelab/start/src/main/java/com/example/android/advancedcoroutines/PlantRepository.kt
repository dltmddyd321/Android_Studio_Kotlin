/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.advancedcoroutines

import androidx.annotation.AnyThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.android.advancedcoroutines.util.CacheOnSuccess
import com.example.android.advancedcoroutines.utils.ComparablePair
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Repository module for handling data operations.
 *
 * This PlantRepository exposes two UI-observable database queries [plants] and
 * [getPlantsWithGrowZone].
 *
 * To update the plants cache, call [tryUpdateRecentPlantsForGrowZoneCache] or
 * [tryUpdateRecentPlantsCache].
 */
@FlowPreview
class PlantRepository private constructor(
    private val plantDao: PlantDao,
    private val plantService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    /**
     * Fetch a list of [Plant]s from the database.
     * Returns a LiveData-wrapped List of Plants.
     */
    val plants : LiveData<List<Plant>> = liveData {
        val plantsLiveData = plantDao.getPlants()
        val customSortOrder = plantsListSortOrderCache.getOrAwait()
        emitSource(plantsLiveData.map {
            plantList -> plantList.applySort(customSortOrder)
        })
    }

    private val customSortFlow by lazy {
        plantsListSortOrderCache::getOrAwait.asFlow()
        .onStart {
            emit(listOf())
            delay(1500)
        }
    }

    val plantsFlow : Flow<List<Plant>>
        get() = plantDao.getPlantsFlow()
            //두 연산자 흐름을 결합 -> 두 흐름 모두 자체 코루틴에서 실행되고, 각 흐름에서 새 값이 생성될 때마다 각 흐름의 최신 값을 변환 호출
            .combine(customSortFlow) { plants, sortOrder ->
                plants.applySort(sortOrder)
            }
                //새 코루틴을 실행하여 flowOn 호출 이전에 흐름을 실행하고 수집
                //버퍼를 도입하여 새 코루틴의 결과를 이후 호출로 전송
            .flowOn(defaultDispatcher)
            //해당 버퍼의 값을 flowOn 이후에 Flow에 방출
            .conflate()

    fun getPlantsWithGrowZoneFlow(growZoneNumber: GrowZone) : Flow<List<Plant>> {
        return plantDao.getPlantsWithGrowZoneNumberFlow(growZoneNumber.number)
            .map { plantList ->
                val sortOrderFromNetwork = plantsListSortOrderCache.getOrAwait()
                val nextValue = plantList.applyMainSafeSort(sortOrderFromNetwork)
                nextValue
            }
    }

    /**
     * Fetch a list of [Plant]s from the database that matches a given [GrowZone].
     * Returns a LiveData-wrapped List of Plants.
     */
//    fun getPlantsWithGrowZone(growZone: GrowZone) = liveData {
//        val plantsGrowZoneLiveData = plantDao.getPlantsWithGrowZoneNumber(growZone.number)
//        val customSortOrder = plantsListSortOrderCache.getOrAwait()
//        emitSource(plantsGrowZoneLiveData.map { plantList ->
//            plantList.applySort(customSortOrder)
//        })
//    }

    @AnyThread
    suspend fun List<Plant>.applyMainSafeSort(customSortOrder: List<String>) =
        withContext(defaultDispatcher) {
            this@applyMainSafeSort.applySort(customSortOrder)
        }

    //값이 수신될 때마다 새 LiveData를 가리킨다.
    //코루틴은 관찰되면 실행되기 시작하고 완료 시 또는 DB나 네트워크의 호출에 실패하는 경우 실행이 종료
    //단, 캐시되어 있으므로 매핑에서 네트워크 호출을 실행해도 안전
    fun getPlantsWithGrowZone(growZone: GrowZone) =
        plantDao.getPlantsWithGrowZoneNumber(growZone.number)
            .switchMap { plantList ->
                liveData {
                    val customSortOrder = plantsListSortOrderCache.getOrAwait()
                    emit(plantList.applyMainSafeSort(customSortOrder))
                }
            }

    /**
     * Returns true if we should make a network request.
     */
    private fun shouldUpdatePlantsCache(): Boolean {
        // suspending function, so you can e.g. check the status of the database here
        return true
    }

    /**
     * Update the plants cache.
     *
     * This function may decide to avoid making a network requests on every call based on a
     * cache-invalidation policy.
     */
    suspend fun tryUpdateRecentPlantsCache() {
        if (shouldUpdatePlantsCache()) fetchRecentPlants()
    }

    /**
     * Update the plants cache for a specific grow zone.
     *
     * This function may decide to avoid making a network requests on every call based on a
     * cache-invalidation policy.
     */
    suspend fun tryUpdateRecentPlantsForGrowZoneCache(growZoneNumber: GrowZone) {
        if (shouldUpdatePlantsCache()) fetchPlantsForGrowZone(growZoneNumber)
    }

    /**
     * Fetch a new list of plants from the network, and append them to [plantDao]
     */
    private suspend fun fetchRecentPlants() {
        val plants = plantService.allPlants()
        plantDao.insertAll(plants)
    }

    /**
     * Fetch a list of plants for a grow zone from the network, and append them to [plantDao]
     */
    private suspend fun fetchPlantsForGrowZone(growZone: GrowZone) {
        val plants = plantService.plantsByGrowZone(growZone)
        plantDao.insertAll(plants)
    }

    //맞춤 정렬 순서를 위한 메모리 내 캐시로 사용
    private var plantsListSortOrderCache =
        CacheOnSuccess(onErrorFallback = { listOf<String>() }) {
            plantService.customPlantSortOrder()
        }

    //몰록을 다시 정렬하여 customSortOrder에 있는 Plants를 목록 앞부분에 배치
    private fun List<Plant>.applySort(customSortOrder: List<String>) :List<Plant> {
        return sortedBy { plant ->
            val positionForItem = customSortOrder.indexOf(plant.plantId).let { order ->
                if(order > -1) order else Int.MAX_VALUE
            }
            ComparablePair(positionForItem, plant.name)
        }
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao, plantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: PlantRepository(plantDao, plantService).also { instance = it }
            }
    }
}
