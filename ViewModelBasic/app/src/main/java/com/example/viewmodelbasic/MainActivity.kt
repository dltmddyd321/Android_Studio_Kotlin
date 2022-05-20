package com.example.viewmodelbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
View 모델 사용 이유
1. UI와 데이터의 상태 일치 보장
2. 메모리 누수 없음
3. 비정상적인 크래쉬 없음
4. 리소스 공유
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}