package com.example.composestudy

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class TestWorkManager(context: Context) {

    private val workManager = WorkManager.getInstance(context)

    private fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .build()

    private fun createWorkRequest() =
        PeriodicWorkRequestBuilder<LogWorker>(15, TimeUnit.MINUTES) // 15분마다 반복
            .setConstraints(createConstraints())
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS, TimeUnit.MILLISECONDS
            )
            .setInitialDelay(10, TimeUnit.MINUTES)
            .build()

    fun reserveWork() {
        val work = createWorkRequest()
        workManager.enqueueUniquePeriodicWork(
            LogWorker::class.java.name,
            ExistingPeriodicWorkPolicy.KEEP,
            work
        )

        val state = workManager.getWorkInfosForUniqueWork(LogWorker::class.java.name).get()
        for (i in state) {
            Log.d("TestWorkManager", "startWorkManager: $state")
        }
    }

    fun cancelWork() {
        workManager.cancelUniqueWork(LogWorker::class.java.name)

        val state = workManager.getWorkInfosForUniqueWork(LogWorker::class.java.name).get()
        for (i in state) {
            Log.d("TestWorkManager", "startWorkManager: $state")
        }
    }
}