package com.example.mvpbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
Model은 데이터의 처리, View는 데이터의 출력, Presenter는 MV의 중재자 -> MVP 패턴
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}