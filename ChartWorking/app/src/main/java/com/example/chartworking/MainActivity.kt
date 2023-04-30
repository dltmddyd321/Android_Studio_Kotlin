package com.example.chartworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chartworking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnBarChart.setOnClickListener {
                startActivity(Intent(this@MainActivity, BarChartActivity::class.java))
            }
            btnDonutChart.setOnClickListener {
                startActivity(Intent(this@MainActivity, DonutChartActivity::class.java))
            }
            btnLineChart.setOnClickListener {
                startActivity(Intent(this@MainActivity, LineChartActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}