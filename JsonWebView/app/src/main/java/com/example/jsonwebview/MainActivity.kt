package com.example.jsonwebview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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
        webView.webViewClient = JsonWebViewClient()
        setContentView(webView)

        executorService.execute {
//            val doc = Jsoup.connect(URL).get()
//            Log.d("TestPlay", doc.toString())
            val result = getJsonObject(URL)
            Log.d("TestPlay", result.toString())
            runOnUiThread {
                Toast.makeText(this, result.getString("limit_date"), Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class JsonWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()
            view?.loadUrl(url)
            Log.d("TestURL", url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.d("WebName", "Result : $url")
        }
    }

    private fun getJsonObject(url : String) : JSONObject {
        val client = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        val response = client.newCall(request).execute()
        return JSONObject(response.body()?.string())
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