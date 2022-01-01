package com.example.delegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var textView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        var test : Boolean by Delegates.observable(true) { _, oldValue, newValue ->
            Toast.makeText(this, "$oldValue 변수가 $newValue 변경!", Toast.LENGTH_SHORT).show()
        }

        textView.setOnClickListener {
            test = false
        }
    }
}