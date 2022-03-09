package com.ebookfrenzy.explicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

import com.ebookfrenzy.explicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //어떤 서브 Activity가 데이터를 반환한 것인지 확인 시 사용되는 코드 번호
    private val request_code = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    fun sendText(view: View) {
//        val i = Intent(this, ActivityB::class.java)
//        val myString = binding.editText1.text.toString()
//        i.putExtra("qString", myString)
//        startActivityForResult(i, request_code)
//    }

    fun sendText(view : View) {
        val i = Intent(this, ActivityB::class.java)
        val myString = binding.editText1.text.toString()
        i.putExtra("qString", myString)
        startActivity(i)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == request_code) &&
            (resultCode == RESULT_OK)) {
            data?.let {
                if (it.hasExtra("returnData")) {
                    val returnString = it.extras?.getString("returnData")
                    binding.textView1.text = returnString
                }
            }
        }
    }
}