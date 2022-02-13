package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.concurrent.Flow

class PrefSettingManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "dataStore")
    private val stringKey = stringPreferencesKey("key_name") // string 저장 키값
    private val intKey = intPreferencesKey("key_name") // int 저장 키값

    val text =  context.dataStore.data
        .catch { exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefs ->
            prefs[stringKey] ?: ""
        }

    suspend fun setText(text : String) {
        context.dataStore.edit { prefs ->
            prefs[stringKey] = text
        }
    }
}

