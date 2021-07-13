package com.example.pushevent

import android.annotation.SuppressLint
import android.content.Intent
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
        updateResult()
        //Default 값을 지정했기 때문에 따로 ()안에 넣을 필요 X
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)
        //새로 들어오는 intent로 교체해야 데이터 또한 갱신 가능

        updateResult(true)
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task -> //파이어베이스의 토큰 메시지를 서버에서 가져오기
                if(task.isSuccessful) {
                    firebaseToken.text = task.result
                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun updateResult(isNewIntent: Boolean = false) {
        //앱이 새로 실행되었는지 알림창을 통해 갱신했는지 여부

        resultTextView.text = (intent.getStringExtra("notificationType") ?: "앱 런처") + if(isNewIntent) {
            "(으)로 갱신 완료"
        } else {
            "(으)로 실행 완료"
        }
    } //intent를 눌렀을때는 notification을 통한 갱신 완료임을 알리고, intent가 아닌 기본 실행이라면 실행 완료 텍스트 팝업
}