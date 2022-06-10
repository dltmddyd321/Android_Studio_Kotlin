package com.example.hiltbasic.market

import android.util.Log
import com.example.hiltbasic.TAG
import javax.inject.Inject

class ClothingMarket @Inject constructor() : Market {
    override fun open() {
        Log.i(TAG, "open clothing market")
    }

    override fun close() {
        Log.i(TAG, "close clothing market")
    }
}