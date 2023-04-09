package com.movingroot.flowassignment.presentation.ui.detail

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomLoadWebViewClient(
    private val pageStartCallback: (() -> Unit)? = null,
    private val pageFinishCallback: (() -> Unit)? = null
) : WebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        pageStartCallback?.invoke()
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        pageFinishCallback?.invoke()
    }
}
