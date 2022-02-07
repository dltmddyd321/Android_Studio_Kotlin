package com.example.datastore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory

class PrefsApplication : Application() {

    private lateinit var dataStore : PrefSettingManager

    companion object {
        private lateinit var prefsApplication : PrefsApplication
        fun getInstance() : PrefsApplication = prefsApplication
    }

    override fun onCreate() {
        super.onCreate()
        prefsApplication = this
        dataStore = PrefSettingManager(this)
    }

    fun getDataStore() : PrefSettingManager = dataStore
}