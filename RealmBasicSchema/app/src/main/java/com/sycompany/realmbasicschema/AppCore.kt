package com.sycompany.realmbasicschema

import androidx.multidex.MultiDexApplication
import io.realm.Realm

class AppCore : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmName = "user.realm"
        val config = io.realm.RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .name(realmName)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)
    }
}