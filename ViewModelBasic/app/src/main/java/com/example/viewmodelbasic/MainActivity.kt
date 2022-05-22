package com.example.viewmodelbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasic.databinding.ActivityMainBinding

/*
View 모델 사용 이유
1. UI와 데이터의 상태 일치 보장
2. 메모리 누수 없음
3. 비정상적인 크래쉬 없음
4. 리소스 공유
 */
class MainActivity : AppCompatActivity() {

    private lateinit var model : NameViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        model = ViewModelProvider(this).get(NameViewModel::class.java)
        binding.name = model

        model.currentName.observe(this, Observer {
            binding.textOutput.text = it.toString()
        })

        binding.btnSave.setOnClickListener {
            binding.textOutput.text = binding.editName.text.toString()
            binding.editName.text.clear()
        }
    }
}