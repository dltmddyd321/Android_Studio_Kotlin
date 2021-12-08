package com.example.singletontest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity - onCreate() called")

        //메모리 주소가 다름
        val myNormalClass1 = Normal()
        val myNormalClass2 = Normal()

        //메모리 주소가 같음
        val mySingletonClass1 = Singleton
        val mySingletonClass2 = Singleton

        //DB 메모리 주소가 다름
        val mySqlOpenHelper_1 = SqlOpenHelper(this)
        val mySqlOpenHelper_2 = SqlOpenHelper(this)

        //DB 메모리 주소가 같음
        val mySqlOpenHelperSingleton_1 = SqlOpenHelperSingleton.getInstance(this)
        val mySqlOpenHelperSingleton_2 = SqlOpenHelperSingleton.getInstance(this)
    }
}