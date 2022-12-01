package com.sycompany.tooltipbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.skydoves.balloon.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        val balloon = Balloon.Builder(this)
            .setWidthRatio(0.2f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText("Today")
            .setTextColorResource(R.color.white)
            .setTextSize(13f)
//            .setIconDrawableResource(R.drawable.ic_launcher_background)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(10)
            .setCornerRadius(8f)
            .setBackgroundColorResource(R.color.black)
            .setBalloonAnimation(BalloonAnimation.FADE)
            .build()

        textView.setOnClickListener{
            balloon.showAlignTop(it)
            balloon.dismissWithDelay(1000L)
        }
    }
}