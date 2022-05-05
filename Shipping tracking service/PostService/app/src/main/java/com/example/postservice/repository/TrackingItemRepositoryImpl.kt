package com.example.postservice.repository

import com.example.postservice.api.SweetTrackerApi
import com.example.postservice.db.TrackingDao
import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class TrackingItemRepositoryImpl (
    private val trackerApi : SweetTrackerApi,
    private val trackingItemDao : TrackingDao,
    private val dispatcher : CoroutineDispatcher) : TrackingItemRepository {

    override suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>> = withContext(dispatcher) {
        trackingItemDao.getAll()
            .mapNotNull { trackingItem ->
                val relatedTrackingInfo = trackerApi.getTrackingInformation(
                    trackingItem.company.code,
                    trackingItem.invoice
                ).body()

                if (!relatedTrackingInfo?.errorMessage.isNullOrBlank()) {
                    null
                } else {
                    trackingItem to relatedTrackingInfo!!
                }
            }
            .sortedWith(
                compareBy(
                    //반환 기준 및 순서에 따라 우선 순위를 정한다.
                    { it.second.level },
                    { -(it.second.lastDetail?.time ?: Long.MAX_VALUE) }
                )
            )
    }
}