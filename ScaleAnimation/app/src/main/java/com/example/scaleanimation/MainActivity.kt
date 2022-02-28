package com.example.scaleanimation

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animTransRight : Animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale)
        val animTransLeft : Animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_alpha)
        val animTransAlpha : Animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_point)
        val animTransTwits : Animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_rotate)

        btn_right.setOnClickListener {
            testImg.startAnimation(animTransRight)
        }

        btn_left.setOnClickListener {
            testImg.startAnimation(animTransLeft)
        }

        btn_alpha.setOnClickListener {
            testImg.startAnimation(animTransAlpha)
        }

        btn_twit.setOnClickListener {
            testImg.startAnimation(animTransTwits)
        }
    }
}