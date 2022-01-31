package com.example.sendbirdchat

import android.app.Application
import com.sendbird.android.SendBird

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SendBird.init(getString(R.string.sendbird_key), applicationContext)
    }
}