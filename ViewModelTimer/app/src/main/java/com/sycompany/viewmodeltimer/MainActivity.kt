package com.sycompany.viewmodeltimer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sycompany.viewmodeltimer.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var timerViewModel: TimerViewModel
    lateinit var binding : ActivityMainBinding
    private var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        binding.viewModel = timerViewModel

        binding.lifecycleOwner = this
        setContentView(binding.root)


        val timer = Timer()
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                cntWork(timer)
            }
        }
        timer.schedule(timerTask, 0, 3000)

    }

    private fun cntWork(timer: Timer) {
        Log.d("TIMER","${cnt}Work!")
        if (cnt >= 10) timer.cancel()
        cnt ++
    }
}