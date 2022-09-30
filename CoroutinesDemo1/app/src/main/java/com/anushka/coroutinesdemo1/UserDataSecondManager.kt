package com.anushka.coroutinesdemo1

import kotlinx.coroutines.*

class UserDataSecondManager {
    var count = 0
    lateinit var deferred: Deferred<Int>
    suspend fun getTotalUserCount(): Int {

        //하위 코루틴으로서 일시 정지 효과 가능 따라서 총 120 반환
        coroutineScope {
            //여기서 디스패처 선언해주지 않으면 부모 코루틴의 디스패처를 그대로 이어 받는다.
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }

            deferred = async(Dispatchers.IO) {
                delay(3000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}