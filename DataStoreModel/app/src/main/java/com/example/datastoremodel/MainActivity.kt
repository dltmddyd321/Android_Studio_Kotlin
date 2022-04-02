package com.example.datastoremodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkData()
    }

    private fun checkData() {
        CoroutineScope(Dispatchers.Main).launch {
            val text = "My Sample!"
            SampleApplication.getInstance().getDataStore().setText(text)
            Log.d("Result", SampleApplication.getInstance().getDataStore().text.first())
        }
    }
}