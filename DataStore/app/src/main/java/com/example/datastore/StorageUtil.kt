package com.example.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StorageUtil<T>(private val context: Context) {

    private val preferenceName = "GenericPrefs"
    private val sharedPreference: SharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun setPref(key: String?, value: T) {
        val editor = sharedPreference.edit()
        when (value) {
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Set<*> -> editor.putStringSet(key, value as Set<String>)
            is String -> editor.putString(key, value as String)
            is Float -> editor.putFloat(key, value as Float)
            is Long -> editor.putLong(key, value as Long)
            is Int -> editor.putInt(key, value as Int)
            else -> {
                val json = Gson().toJson(value)
                editor.putString(key, json).apply()
            }
        }
        editor.apply()
    }

    fun getPref(key: String?, defaultValue: T? = null): T? {
        when (defaultValue) {
            is Boolean -> {
                return sharedPreference.getBoolean(key, defaultValue) as T?
            }
            is Collection<*> -> {
                val result = sharedPreference.getStringSet(key, HashSet())
                return result as T?
            }
            is String -> {
                val ret = sharedPreference.getString(key, defaultValue as String?)
                return ret as T?
            }
            is Float -> {
                val result = sharedPreference.getFloat(key, (defaultValue as Float?)!!)
                return result as T?
            }
            is Long -> {
                val result = sharedPreference.getLong(key, (defaultValue as Long?)!!)
                return result as T?
            }
            is Int -> {
                val result = sharedPreference.getInt(key, (defaultValue as Int?)!!)
                return result as T?
            }
            else -> {
                val ret: String? = sharedPreference.getString(key, null)

                // Get correct type of T for deserializing the json
                val returnType: Type = object : TypeToken<T>() {}.type
                return Gson().fromJson(ret, returnType)
            }
        }
    }

    fun removePref(key: String) {
        sharedPreference.edit().remove(key).apply()
    }

    fun removeAllPrefs() {
        sharedPreference.edit().clear().apply()
    }
}