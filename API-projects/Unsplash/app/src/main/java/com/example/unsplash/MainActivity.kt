package com.example.unsplash

import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.displayhash.DisplayHashManager
import android.widget.Toast
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchRandomPhotos()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun fetchRandomPhotos(query: String ?= null) = scope.launch {
        Repository.getRandomPhotos(query)?.let { photos ->
            photos
        }
    }
}