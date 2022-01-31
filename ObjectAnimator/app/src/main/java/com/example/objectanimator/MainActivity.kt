package com.example.objectanimator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlin.io.path.Path

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButton = findViewById<ImageButton>(R.id.imageView)
        imageButton.setOnClickListener {
//            ObjectAnimator.ofFloat(imageButton, "translationX", 100f).apply {
//                duration = 4000
//                start()
//            }
            val x = ObjectAnimator.ofFloat(imageButton, "translationX", 800f)
            val y = ObjectAnimator.ofFloat(imageButton, "translationY", 800f)
            val AnimatorSet = AnimatorSet()
            AnimatorSet.playTogether(x,y)
            AnimatorSet.duration = 1000
            AnimatorSet.start()
        }
    }
}