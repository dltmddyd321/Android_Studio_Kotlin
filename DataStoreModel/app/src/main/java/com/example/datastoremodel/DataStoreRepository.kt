package com.example.datastoremodel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("TEST_STORE")

class DataStoreRepository(context: Context) {
    private val dataStore = context.dataStore

    private val SAVED_NAME = stringPreferencesKey("name")
    private val SAVED_AGE = intPreferencesKey("age")

    suspend fun setName(newName: String) {
        dataStore.edit { preferences ->
            preferences[SAVED_NAME] = newName
        }
    }

    val getName: Flow<String> = dataStore.data.map {
        it[SAVED_NAME] ?: "unNamed"
    }

    suspend fun setAge(newAge: Int) {
        dataStore.edit { preferences ->
            preferences[SAVED_AGE] = newAge
        }
    }

    val getAge: Flow<Int> = dataStore.data.map {
        it[SAVED_AGE] ?: 0
    }
}

