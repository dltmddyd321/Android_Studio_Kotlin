package com.example.inlineclasstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TEST", "IO Thread")
            var cnt = 0

            kotlin.runCatching {
                cnt ++
            }.onSuccess {
                Log.d("TEST", "$cnt")
            }.onFailure {
                Log.d("TEST", "$cnt")
            }.fold({
                   // 성공시에만 실행
            }, {
                    //실패
            })
        }
    }
}