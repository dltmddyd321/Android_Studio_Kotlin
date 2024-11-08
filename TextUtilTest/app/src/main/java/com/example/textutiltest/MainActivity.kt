package com.example.textutiltest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.textutiltest.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.*
import kotlin.Comparator

class MainActivity : AppCompatActivity(), TestInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onTestStart(cnt: Int) {
        super.onTestStart(cnt)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.numberEdit.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.instaBtn.setOnClickListener {
            val likeIng = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/_u/win_dr.0"))
            likeIng.setPackage("com.instagram.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/win_dr.0/feed")))
            }
        }

        val cutText = "Base g83fdj90jd=="

        val token = cutText.split(" ")

        Log.d("CUT", token.toString())

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("beforeTextChanged", " s: $s, start: $start, count: $count, after: $after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("onTextChanged", "s: $s, start: $start, before: $before, count: $count")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("afterTextChanged", "s: $s")
            }
        })

//        Toast.makeText(this, nullTest(), Toast.LENGTH_SHORT).show()
    }

    private fun nullTest(): String {
        var msg: String? = null
        return msg!!
    }

    data class Person(var name: String, var age: Int)

    private fun personSort() {
        val arr =
            arrayOf(Person("Park", 10), Person("Kim", 12), Person("Choi", 5), Person("Lee", 19))

        //오름차순 정렬
        arr.sortWith { o1, o2 ->
            o1.age - o2.age
        }

        println(arr.contentToString())
    }

    private fun personCompare() {
        val arr =
            arrayOf(Person("Park", 10), Person("Kim", 12), Person("Kim", 5), Person("Lee", 19))

        arr.sortWith(compareBy({ it.name }, { it.age })) //먼저 이름 기준 오름차순 정렬, 이후 나이 기준 오름차순 정렬

        println(arr.contentToString())
    }
}

