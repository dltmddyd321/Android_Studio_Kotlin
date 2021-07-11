package com.example.simplewebbrowser

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.ContentLoadingProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private val refreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.refreshLayout)
    }

    private val goHomeBtn: ImageButton by lazy {
        findViewById(R.id.goHome)
    }

    private val progressBar: ContentLoadingProgressBar by lazy {
        findViewById(R.id.progressBar)
    }

    private val addressBar: EditText by lazy {
        findViewById(R.id.address)
    }

    private val goBackBtn: ImageButton by lazy {
        findViewById(R.id.goBack)
    }

    private val goFowardBtn: ImageButton by lazy {
        findViewById(R.id.goForward)
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

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            //웹 뷰 영역에 인터넷이 나타나도록 함
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            //웹 뷰에서 자바스크립트를 지원하도록 함
            loadUrl(DEFAULT_URL)
        }
        //apply를 통해 webView를 3번 호출하는 문제 제거
    }

    private fun bindViews() {
        goHomeBtn.setOnClickListener {
            webView.loadUrl(DEFAULT_URL)
        }

        addressBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                //주소창에 주소 입력 후 Enter 시
                val loadingUrl = v.text.toString()
                if(URLUtil.isNetworkUrl(loadingUrl)) {
                    webView.loadUrl(v.text.toString())
                }
                webView.loadUrl("http://$loadingUrl")
            } //http를 자동적으로 붙이도록 구현
            return@setOnEditorActionListener false
        }

        goBackBtn.setOnClickListener {
            webView.goBack()
        }

        goFowardBtn.setOnClickListener {
            webView.goForward()
        }

        refreshLayout.setOnRefreshListener {
            webView.reload()
        }
    }

    inner class WebViewClient: android.webkit.WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            progressBar.show()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            refreshLayout.isRefreshing = false
            progressBar.hide()
            //로딩 완료 시 삭제

            goBackBtn.isEnabled = webView.canGoBack()
            goFowardBtn.isEnabled = webView.canGoForward()
            //앞 페이지, 뒤 페이지 이동 간의 버튼 활성화

            addressBar.setText(url)
            //주소 입력 창에 로딩된 url Text가 나타나게 함
        }
    }

    inner class WebChromeClient: android.webkit.WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)

            progressBar.progress = newProgress
        }
    }

    companion object { //지정된 상수 값 설정
        private const val DEFAULT_URL = "http://www.google.com"
    }
}