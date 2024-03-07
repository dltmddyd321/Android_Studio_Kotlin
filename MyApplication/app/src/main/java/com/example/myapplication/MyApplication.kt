package com.example.myapplication

import android.app.Application
import org.greenrobot.eventbus.EventBus

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
    }

    override fun onTerminate() {
        EventBus.getDefault().unregister(this)
        super.onTerminate()
    }
}