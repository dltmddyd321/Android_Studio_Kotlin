package com.example.webcrawlingtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchSite = "https://google.co.kr/search?q="
        val keyword = "naver"

        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(searchSite + keyword).get()
            withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
                Log.d("TEST", doc.toString())

                Log.d("TEST", doc.location() + doc.title())
            }
        }
    }
}