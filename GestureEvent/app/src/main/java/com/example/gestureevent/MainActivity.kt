package com.example.gestureevent

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var gestureDetector: GestureDetector
    lateinit var gestureView : View

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txtView)
        gestureView = findViewById(R.id.gestureView)

        gestureView.setOnTouchListener { _, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
            true
        }

        gestureDetector = GestureDetector(this, MyGesture())

    }

    inner class MyGesture : GestureDetector.OnGestureListener {
        override fun onDown(p0: MotionEvent?): Boolean {
            textView.append("onDown Call" + "\n")
            return true
        }

        override fun onShowPress(p0: MotionEvent?) {
            textView.append("onPress Call" + "\n")
        }

        override fun onSingleTapUp(p0: MotionEvent?): Boolean {
            textView.append("onSingleTabUp Call" + "\n")
            return true
        }

        override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
            textView.append("onScroll Call ($p2 * $p3)\n")
            return true
        }

        override fun onLongPress(p0: MotionEvent?) {
            textView.append("onLongPress Call" + "\n")
        }

        override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
            textView.append("onScroll Call ($p2 * $p3)\n")
            return true
        }
    }
}