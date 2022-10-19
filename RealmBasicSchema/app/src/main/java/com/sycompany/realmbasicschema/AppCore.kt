package com.sycompany.realmbasicschema

import androidx.multidex.MultiDexApplication
import io.realm.FieldAttribute
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
            .schemaVersion(3)
            .migration { realm, oldVersion, newVersion ->
                val schema = realm.schema
                if (oldVersion == 1L) {
                    val mPersonSchema = schema["User"]
                    mPersonSchema?.addField("home", String::class.java, null)
                }

                if (oldVersion == 2L) {
                    schema.create("Pet")
                        .addField("id", Int::class.javaObjectType, FieldAttribute.PRIMARY_KEY)
                        .addField("value", String::class.javaObjectType)
                        .addField("name", Int::class.javaObjectType)
                        .addField("age", Int::class.javaObjectType)

                }
            }
            .build()

        Realm.setDefaultConfiguration(config)
    }
}