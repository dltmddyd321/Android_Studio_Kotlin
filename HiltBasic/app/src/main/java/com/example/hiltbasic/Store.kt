package com.example.hiltbasic

import android.util.Log
import javax.inject.Inject

const val TAG = "STORE"

class Store @Inject constructor() {

    fun open() {
        Log.i(TAG, "OPEN")
    }

    fun close() {
        Log.i(TAG, "CLOSE")
    }
}