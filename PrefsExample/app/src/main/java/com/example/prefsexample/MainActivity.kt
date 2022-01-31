package com.example.prefsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_done.setOnClickListener {
            val id = register_id.text.toString()
            val pw = register_pw.text.toString()

            //Prefs 객체 얻어오기 -> 해당 Prefs.xml 이름 지정
//            val sharedPreference = getSharedPreferences("other", MODE_PRIVATE)
//            val editor = sharedPreference.edit()

            MyApplication.prefs.setString("id",id)
            MyApplication.prefs.setString("pw",pw)

            if(id == "") {
                Toast.makeText(this, "ID를 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "회원가입 완료! ID = $id", Toast.LENGTH_SHORT).show()
            }
        }
    }
}