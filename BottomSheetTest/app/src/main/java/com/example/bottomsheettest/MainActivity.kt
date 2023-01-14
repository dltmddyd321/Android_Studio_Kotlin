package com.example.bottomsheettest

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomsheettest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gradientDrawable = this.getDrawable(R.drawable.backgroud_test) as GradientDrawable
        gradientDrawable.setStroke(2, this.getColor(R.color.purple_200))
        binding.testView.foreground = gradientDrawable
    }
}