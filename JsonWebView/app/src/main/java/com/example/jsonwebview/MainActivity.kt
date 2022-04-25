package com.example.jsonwebview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    val USER_AGENT = "Mozilla/5.0 AppleWebKit/535.19 Chrome/56.0.0 Mobile Safari/535.19"
    val URL = "https://img.gettimeblocks.com/maintenance/limitedPeriodOfServiceProvision.json"
    private lateinit var webView : WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccess = true
        webSettings.userAgentString = USER_AGENT
        webView.loadUrl(URL)
        webView.webViewClient = WebViewClient()
        setContentView(webView)

        executorService.execute {
            val doc = Jsoup.connect(URL).get()
            Log.d("TestPlay", doc.toString())
        }
    }

    inner class JsonWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()
            view?.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.d("WebName", "Result : $url")
        }
    }

//    private fun getJsonObject(url : String) : String {
//        var inputStream : InputStream? = null
//        var response = ""
//        var result = ""
//        inputStream = URL(url).openStream()
//        val rd = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
//        val buffer = StringBuffer()
//        response = rd.readLine()
//        while (response != null) {
//            buffer.append(response)
//        }
//        result = buffer.toString()
//        return result
//    }
}