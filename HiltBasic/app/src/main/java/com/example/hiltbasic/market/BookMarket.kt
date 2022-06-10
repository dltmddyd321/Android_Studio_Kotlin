package com.example.hiltbasic.market

import android.util.Log
import com.example.hiltbasic.TAG
import javax.inject.Inject

class BookMarket @Inject constructor() : Market {
    override fun open() {
        Log.i(TAG, "open book store")
    }

    override fun close() {
        Log.i(TAG, "close book store")
    }
}