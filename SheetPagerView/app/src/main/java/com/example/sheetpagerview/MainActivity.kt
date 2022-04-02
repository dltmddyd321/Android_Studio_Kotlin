package com.example.sheetpagerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.openbottomsheet)

        btn.setOnClickListener {
            val bottomSheet = BottomSheet()
            bottomSheet.show(supportFragmentManager, "TAG")
        }
    }
}