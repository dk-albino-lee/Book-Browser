package com.movingroot.flowassignment.presentation.ui.detail

import android.graphics.Bitmap
import android.os.Message
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class ChromeClient(
    private val pageStartCallback: (() -> Unit)? = null,
    private val pageFinishCallback: (() -> Unit)? = null,
    private val closeWindowCallback: ((v: WebView) -> Unit)? = null
) : WebChromeClient() {
    override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message): Boolean {
        val childView = WebView(view.context)
        childView.settings.javaScriptEnabled = true
        val transport = resultMsg.obj as WebView.WebViewTransport
        transport.webView = childView
        resultMsg.sendToTarget()

        childView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                pageStartCallback?.invoke()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                pageFinishCallback?.invoke()
            }
        }

        return true
    }

    override fun onCloseWindow(window: WebView) {
        super.onCloseWindow(window)
        closeWindowCallback?.invoke(window)
    }
}
