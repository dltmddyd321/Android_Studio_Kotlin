package com.example.workmanagertask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleWork()

        myWorkMananger()
    }

    private fun myWorkMananger() {
        //일련의 제약 조건을 만들어 작업과 연결하기 위한 객체
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()

        //주기적 작업 예약
        val myRequest = PeriodicWorkRequest.Builder(
            Worker::class.java,
            15,
            TimeUnit.MINUTES
        ).setConstraints(constraints)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "my_id",
                ExistingPeriodicWorkPolicy.KEEP,
                myRequest
            )
    }

    private fun simpleWork() {
        //한번만 실행할 작업의 요청
        val mRequest : WorkRequest = OneTimeWorkRequestBuilder<Worker>()
            .build()

        WorkManager.getInstance(this)
            .enqueue(mRequest)
    }
}