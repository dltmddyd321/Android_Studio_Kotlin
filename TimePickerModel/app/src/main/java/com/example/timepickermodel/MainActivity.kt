package com.example.timepickermodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var textView : TextView
    lateinit var timePicker : TimePicker
    lateinit var datePicker: DatePicker

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        timePicker = findViewById(R.id.dpTimePicker)
        datePicker = findViewById(R.id.dpSpinner)

        timePicker.setOnTimeChangedListener { _, p1, p2 ->
            textView.text = "${p1}시 ${p2}분"
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener { _, p1, p2, p3 ->
                textView.text = "${p1}년 ${p2}월 ${p3}일"
            }
        }
    }
}