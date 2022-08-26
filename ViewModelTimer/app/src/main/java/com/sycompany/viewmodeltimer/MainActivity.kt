package com.sycompany.viewmodeltimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sycompany.viewmodeltimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var timerViewModel: TimerViewModel
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        binding.viewModel = timerViewModel

        binding.lifecycleOwner = this
        setContentView(binding.root)
    }
}