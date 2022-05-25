package com.example.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val person = Person("John K", 27)

        StorageUtil<Person>(this).setPref("person", person)

        Log.d("PERSON", "${StorageUtil<Person>(this).getPref("person", person)}")
    }
}