package com.secipin.v2ex

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import java.util.zip.GZIPInputStream

class WebViewActivity : Activity() {

    private var webView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WebView.setWebContentsDebuggingEnabled(true)

        webView = WebView(this)
        webView?.overScrollMode = View.OVER_SCROLL_NEVER
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.userAgentString = "MicroMessenger"
        webView?.loadDataWithBaseURL("https://www.v2ex.com/", GZIPInputStream(assets.open("data")).bufferedReader().readText(), null, null, null)
        setContentView(webView)
    }

    override fun onBackPressed() {
        webView?.evaluateJavascript("goBack()") { value ->
            if (value == "false") {
                moveTaskToBack(true)
            }
        }
    }
}
