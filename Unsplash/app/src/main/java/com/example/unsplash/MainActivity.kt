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

class MainActivity : AppCompatActivity() {

    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size)
        val width = size.x
        val height = size.y

        Log.d(tag, "$width / $height")

        if(width == 884) {
            Toast.makeText(this,"작은 화면!",Toast.LENGTH_SHORT).show()
        } else if(width == 1768) {
            Toast.makeText(this,"폴드 화면!",Toast.LENGTH_SHORT).show()
        }
        //width fold : 884 / unfold : 1768
    }

//    private fun addComponentCallBackListener() {
//        applicationContext?.registerComponentCallbacks(object : ComponentCallbacks {
//            override fun onConfigurationChanged(p0: Configuration) {
//                checkFoldedDisplay()
//            }
//
//            override fun onLowMemory() {
//            }
//        })
//    }
//
//    fun checkFoldedDisplay() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            val dm = applicationContext.getSystemService(Context.DISPLAY_SERVICE) as? DisplayManager
//            dm?.displays?.forEach {
//                Log.d(tag, "DisplayMode : ${it.mode}")
//            }
//        }
//    }
}