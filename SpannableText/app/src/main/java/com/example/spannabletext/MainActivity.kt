package com.example.spannabletext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.set

class MainActivity : AppCompatActivity() {

    lateinit var txt_example : TextView
    lateinit var txt_example2 : TextView
    lateinit var mContext : Context
    lateinit var img : Drawable

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = applicationContext
        txt_example = findViewById(R.id.txt_example)
        txt_example2 = findViewById(R.id.txt_example2)
        img = resources.getDrawable(R.drawable.ic_launcher_foreground, null)

        val span : Spannable = txt_example.text as Spannable
        span.setSpan(ForegroundColorSpan(Color.RED), 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(UnderlineSpan(), 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(RelativeSizeSpan(1.5f), 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(mContext, "Span Click!!", Toast.LENGTH_SHORT).show()
            }
        }, 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txt_example.movementMethod = LinkMovementMethod.getInstance()

        val drawableSpan : Spannable = txt_example2.text as Spannable
        img.setBounds(0, 0, img.intrinsicWidth, img.intrinsicHeight)
        drawableSpan.setSpan(ImageSpan(img),4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    }
}