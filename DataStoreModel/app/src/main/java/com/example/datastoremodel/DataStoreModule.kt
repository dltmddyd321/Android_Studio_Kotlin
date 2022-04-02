package com.example.datastoremodel

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreModule(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "dataStore")
    private val stringKey = stringPreferencesKey("textKey")

    //데이터를 읽어올 때 데이터는 Flow 객체로 전달
    //map을 통해 데이터 스토어에 저장되어 있는 값을 미리 저장한 키를 통해 가져온다.
    //IOException 예외처리 필수
    val text : Flow<String> = context.dataStore.data
        .catch { exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[stringKey] ?: ""
        }

    suspend fun setText(text: String) {
        context.dataStore.edit { preference ->
            preference[stringKey] = text
        }
    }
}