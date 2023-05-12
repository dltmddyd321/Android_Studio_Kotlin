package com.example.composestudy

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class LogWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        Log.i("WorkManagerTestLog", "백그라운드 동작 정상 처리!")
        return Result.success()
    }
}