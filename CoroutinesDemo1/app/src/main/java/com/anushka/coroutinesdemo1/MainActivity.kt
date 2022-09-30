package com.anushka.coroutinesdemo1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getUserData()
        mainViewModel.users.observe(this, Observer { users ->
            users.forEach {
                Toast.makeText(this, "name is ${it.name}", Toast.LENGTH_SHORT).show()
            }
        })

        CoroutineScope(Dispatchers.IO).launch {
            //10000 delay 후 발동
            val s1 = getStockFirst()
            //18000 delay 후 발동
            val s2 = getStockSecond()
            val total = s1 + s2
            Log.i("MyTag", "total Stock: $total")
        }

        CoroutineScope(Dispatchers.IO).launch {
            //각자 병렬 코루틴 실행
            val s1 = async { getStockFirst() }
            val s2 = async { getStockSecond() }
            val total = s1.await() + s2.await()
            Log.i("MyTag", "total Stock: $total")
        }

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
            }

            //더하기 연산자를 통해 코루틴 Context 병합 가능
//            CoroutineScope(Dispatchers.IO).launch {
//                downloadUserData()
//            }
            //Dispatchers.Default: 대량의 JSON 파싱 등에 사용하기 적절
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun downloadUserData() {
        withContext(Dispatchers.Main) {
            for (i in 1..200000) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
                delay(1000)
            }
        }
    }

    private suspend fun getStockFirst(): Int {
        delay(10000)
        Log.i("MyTag", "stock 1 returned")
        return 55000
    }

    private suspend fun getStockSecond(): Int {
        delay(8000)
        Log.i("MyTag", "stock 2 returned")
        return 35000
    }
}
