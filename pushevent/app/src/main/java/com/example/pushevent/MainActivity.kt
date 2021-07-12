package com.example.pushevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy {
        findViewById(R.id.result)
    }

    private val firebaseToken: TextView by lazy {
        findViewById(R.id.firebasetokenText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task -> //파이어베이스의 토큰 메시지를 서버에서 가져오기
                if(task.isSuccessful) {
                    firebaseToken.text = task.result
                }
            }
    }
}