package com.example.unsplash

import android.app.Application

//Singleton Instance
class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}