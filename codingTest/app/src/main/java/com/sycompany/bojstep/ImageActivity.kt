package com.sycompany.bojstep

import android.annotation.SuppressLint
import android.graphics.Outline
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView

class ImageActivity : AppCompatActivity() {

    private lateinit var image: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        image = findViewById(R.id.testImg)

        val outLine = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height + 240, 60f)
            }
        }

        image.outlineProvider = outLine
        image.clipToOutline = true
    }
}