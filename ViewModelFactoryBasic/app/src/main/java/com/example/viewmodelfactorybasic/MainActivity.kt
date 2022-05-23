package com.example.viewmodelfactorybasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PracticeViewModel
    private lateinit var btn: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, PracticeViewModelFactory(4))[PracticeViewModel::class.java]

        btn = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        setText()
        btn.setOnClickListener {
            increment()
        }
    }

    private fun setText() {
        textView.text = viewModel.cnt.toString()
    }

    private fun increment() {
        viewModel.increment()
        setText()
    }
}