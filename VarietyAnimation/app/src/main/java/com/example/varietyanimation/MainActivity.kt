package com.example.varietyanimation

import android.animation.LayoutTransition
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    lateinit var btn : Button
    lateinit var layout: LinearLayout
    lateinit var params : LinearLayout.LayoutParams
    var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn1)
        btn.setOnClickListener {
            val intent = Intent(this, FadeActivity::class.java)
            startActivity(intent)
        }

        layout = findViewById(R.id.layout)
        params = layout.layoutParams as LinearLayout.LayoutParams

        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event?.action == MotionEvent.ACTION_DOWN) {
            cnt ++

            Handler(Looper.getMainLooper()).postDelayed({
                if(cnt % 2 == 0) {
                    change(2f)
                } else {
                    change(-2f)
                }
            }, 2000)
        }

        return true
    }

    private fun change(v : Float) {
        params.height = ((v*layout.height).toInt())
        layout.layoutParams = params
    }
}