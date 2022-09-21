package com.example.jsonwebview

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.jsoup.Jsoup
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

        //웹뷰의 쿠키를 강제로 삭제하기
//        CookieManager.getInstance().removeAllCookies(null)
//        CookieManager.getInstance().flush()
        webView.webViewClient = JsonWebViewClient()
        webView.addJavascriptInterface(MyJavaScriptInterface(), "Android")
        webView.loadUrl(URL)
        setContentView(webView)

//        executorService.execute {
////            val doc = Jsoup.connect(URL).get()
////            Log.d("TestPlay", doc.toString())
//            val result = getJsonObject(URL)
//            Log.d("TestPlay", result.toString())
//            runOnUiThread {
//                Toast.makeText(this, result.getString("limit_date"), Toast.LENGTH_SHORT).show()
//            }
//        }

        // channel[0] and channel[1] represent the two ports.
        // They are already entangled with each other and have been started.
        val channel: Array<out WebMessagePort> = webView.createWebMessageChannel()

        // Create handler for channel[0] to receive messages.
        channel[0].setWebMessageCallback(object : WebMessagePort.WebMessageCallback() {

            override fun onMessage(port: WebMessagePort, message: WebMessage) {
                Log.d("MSG", "On port $port, received this message: $message")
            }
        })

        // Send a message from channel[1] to channel[0].
        channel[1].postMessage(WebMessage("My secure message"))
    }

    inner class MyJavaScriptInterface {
        @RequiresApi(Build.VERSION_CODES.N)
        @JavascriptInterface
        fun getHtml(html : String) {
            Log.d("자바스크립트", html)
            val result = Html.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            Log.d("result", result)
            html.let {
                val body = Jsoup.parse(it).body()
                val pre = body.select("pre").first()
                Log.d("pre", pre.toString())
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
            view?.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML);")
            initPort(webView)
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

    private fun initPort(webView: WebView) {
        lateinit var port: WebMessagePort
        val channels = webView.createWebMessageChannel()
        port = channels[0]
        port.setWebMessageCallback(object : WebMessagePort.WebMessageCallback() {
            override fun onMessage(port: WebMessagePort?, message: WebMessage?) {
                super.onMessage(port, message)
                Toast.makeText(this@MainActivity, message?.data, Toast.LENGTH_SHORT).show()
            }
        })
        webView.postWebMessage(
            WebMessage("", arrayOf(channels[1])),
            Uri.EMPTY
        )

        port.postMessage(
            WebMessage("Kathy")
        )
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