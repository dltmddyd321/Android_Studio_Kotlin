package com.example.customaddlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telephonyManager : TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        if(telephonyManager.simState == TelephonyManager.SIM_STATE_ABSENT) {
            Toast.makeText(this, "유심 없음!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "유심 존재!!", Toast.LENGTH_SHORT).show()
        }

        val list : MutableList<String> = ArrayList()
        list.add("Kotlin")
        list.add("JSP")
        list.add("JAVA")
        list.add("Python")
        list.add("C++")
        list.add("PHP")

        customView.setData(list)
        customView.setTitle("Languages")
        submitBtn.setOnClickListener {
            Toast.makeText(this, "Selected Data : ${customView.getSelectedData()}", Toast.LENGTH_SHORT).show()
        }
    }
}