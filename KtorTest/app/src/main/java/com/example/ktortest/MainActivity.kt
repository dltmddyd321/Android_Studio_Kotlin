package com.example.ktortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ktortest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var binding: ActivityMainBinding
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job() // CoroutineScope를 위해 job을 할당

        binding.callBtn.setOnClickListener {
            launch(Dispatchers.Main) {
                val result = HttpRequestHelper().requestKtorIo()
                binding.resultText.text = result
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() //Activity 종료시 job 종료
    }
}