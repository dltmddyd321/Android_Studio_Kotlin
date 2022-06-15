package com.example.coroutinemodel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {

    private val RESULT_OK = "OK!"

    @Suppress("OPT_IN_IS_NOT_ENABLED")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flow = flow<String> {
            for(i in 1..10) {
                emit("Hello Flow!")
                delay(1000L)
            }
        }

        GlobalScope.launch {
            flow.buffer().collect {
                //1000L의 간격으로 Hello Flow! 출력
                println(it)
                //기존 1000L 간격에 2000L 간격이 추가되어 출력
                delay(2000L)
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            //메인 쓰레드에 대한 Context이며 UI 갱신이나 Toast 등의 View 작업에 사용된다.
        }

        CoroutineScope(Dispatchers.IO).launch {
            //네트워킹이나 내부 DB 접근 등 백그라운드에서 필요한 작업을 수행할 때 사용된다.
            getResultFromApi()
            withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
                Toast.makeText(this@MainActivity, "Nice!", Toast.LENGTH_SHORT).show()
            }
        }

        CoroutineScope(Dispatchers.Default).launch {
            //크기가 큰 리스트를 다루거나 필터링을 수행하는 등 무거운 연산이 필요한 작업에 사용된다.
        }
    }

    private suspend fun getResultFromApi() : String {
        return RESULT_OK
    }
}

