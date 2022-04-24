package com.day2life.timeblocks.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.day2life.timeblocks.addons.timeblocks.TimeBlocksUser
import com.day2life.timeblocks.util.log.Lo
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL

class GoogleConnectDialog(context: Context) : Dialog(context) {
    private lateinit var webView : WebView
    private val USER_AGENT = "Mozilla/5.0 AppleWebKit/535.19 Chrome/56.0.0 Mobile Safari/535.19"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(context)
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.userAgentString = USER_AGENT
        webView.webViewClient = GoogleCalendarWebViewClient()
        webView.loadUrl(
            "https://accounts.google.com/o/oauth2/v2/auth?" +
                    "scope=https://www.googleapis.com/auth/calendar " +
                    "https://www.googleapis.com/auth/userinfo.email&access_type=offline" +
                    "&include_granted_scopes=true&response_type=code&redirect_uri=" +
                    "https://e969-39-118-41-84.ngrok.io/api/externalConnection/google" +
                    "&client_id=19491223810-oc02molopev4unbp7p5pcckrple9tcl3" +
                    ".apps.googleusercontent.com&state=${TimeBlocksUser.getInstance().authToken}"
        )

        setContentView(webView)

        val displayRectangle = Rect()
        val window = window
        window?.decorView?.getWindowVisibleDisplayFrame(displayRectangle)

        val layoutParams = webView.layoutParams
        layoutParams?.height = (displayRectangle.height() * 0.9f).toInt()
        webView.layoutParams = layoutParams
    }

    inner class GoogleCalendarWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url.toString()
            view?.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            if(url?.contains("externalConnection/google?") == true) {
                Lo.g("연결 결과물 : ${checkConnectResult(url)}")
            }
        }
    }

    fun checkConnectResult(url : String) : String {
        var inputStream : InputStream? = null
        var resStr = ""
        var result = ""
        try {
            inputStream = URL(url).openStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            val buffer = StringBuffer()
            resStr = bufferedReader.readLine()
            while (resStr != null) {
                buffer.append(resStr)
            }
            result = buffer.toString()
            return result
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return result
    }
}