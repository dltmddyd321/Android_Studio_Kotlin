package com.example.hiltbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiltbasic.market.BookMarket
import com.example.hiltbasic.market.ClothingMarket
import com.example.hiltbasic.market.Market
import com.example.hiltbasic.module.BookMarketQualifier
import com.example.hiltbasic.module.ClothingMarketQualifier
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.*
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "HILT"

    @Inject
    lateinit var test : String

    @BookMarketQualifier
    @Inject
    lateinit var bookMarket: Market

    @ClothingMarketQualifier
    @Inject
    lateinit var clothingMarket: Market

    @Inject
    lateinit var httpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = Request.Builder()
            .url("https://www.google.com")
            .header("User-Agent", "OkHttp Test")
            .build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                Log.i(TAG, "Network call - ${response.body}")
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG, "Network call error - ${call}, err msg - ${e.message}")
            }
        })

        bookMarket.open()
        clothingMarket.open()

        Log.d(TAG, "result : $test ")
    }
}