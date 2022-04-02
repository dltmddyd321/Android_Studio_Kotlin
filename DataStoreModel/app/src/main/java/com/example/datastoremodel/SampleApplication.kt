package com.example.datastoremodel

import android.app.Application

class SampleApplication : Application() {

    private lateinit var dataStore : DataStoreModule

    companion object {
        private lateinit var sampleApplication: SampleApplication
        fun getInstance() : SampleApplication = sampleApplication
    }

    override fun onCreate() {
        super.onCreate()
        sampleApplication = this
        dataStore = DataStoreModule(this)
    }

    fun getDataStore() : DataStoreModule = dataStore
}