package com.example.bindingdemo1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.bindingdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.submitButton.setOnClickListener {
            displayGreeting()
        }
        binding.student = getStudent()
    }

    @SuppressLint("SetTextI18n")
    private fun displayGreeting() {
//        binding.apply {
//            greetingTextView.text = "Hello! " + nameEditText.text
//        }
        binding.apply {
            if (progressBar.visibility == View.GONE) {
                progressBar.visibility = View.VISIBLE
                submitButton.text = "STOP"
            } else {
                progressBar.visibility = View.GONE
                submitButton.text = "START"
            }
        }
    }

    private fun getStudent(): Student {
        return Student(1, "LeeSY", "dinoinventor@naver.com")
    }
}