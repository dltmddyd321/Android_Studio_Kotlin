package com.example.simplewebbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val addressBar: EditText by lazy {
        findViewById(R.id.address)
    }

    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            //웹 뷰 영역에 인터넷이 나타나도록 함
            settings.javaScriptEnabled = true
            //웹 뷰에서 자바스크립트를 지원하도록 함
            loadUrl("http://www.google.com")
        }
        //apply를 통해 webView를 3번 호출하는 문제 제거
    }

    private fun bindViews() {
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                //주소창에 주소 입력 후 Enter 시
                webView.loadUrl(v.text.toString())
            }
            return@setOnEditorActionListener false
        }
    }
}