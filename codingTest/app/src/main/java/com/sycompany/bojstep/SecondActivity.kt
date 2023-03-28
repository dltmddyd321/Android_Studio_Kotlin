package com.sycompany.bojstep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    private lateinit var backBtn : Button
    private lateinit var inputName : EditText
    private lateinit var inputAge : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        backBtn = findViewById(R.id.backBtn)
        inputName = findViewById(R.id.inputName)
        inputAge = findViewById(R.id.inputAge)

        backBtn.setOnClickListener {
            if (inputName.text.isNullOrBlank() || inputAge.text.isNullOrBlank()) {
                Toast.makeText(this, "이름과 나이를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            intent.putExtra("name",inputName.text.toString())
            intent.putExtra("age",inputAge.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}