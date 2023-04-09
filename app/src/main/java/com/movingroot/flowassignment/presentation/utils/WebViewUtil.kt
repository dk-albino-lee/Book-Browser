package com.movingroot.flowassignment.presentation.utils

import android.webkit.WebSettings
import android.webkit.WebView
import com.movingroot.flowassignment.presentation.ui.detail.ChromeClient
import com.movingroot.flowassignment.presentation.ui.detail.CustomLoadWebViewClient

object WebViewUtil {
    fun WebSettings.setBasics() = this.apply {
        javaScriptEnabled = true
        domStorageEnabled = true
        javaScriptCanOpenWindowsAutomatically = true
        cacheMode = WebSettings.LOAD_NO_CACHE
        loadsImagesAutomatically = true
    }

    fun WebView.setClients(
        chromeClient: ChromeClient,
        webViewClient: CustomLoadWebViewClient
    ) = this.apply {
        this.webChromeClient = chromeClient
        this.webViewClient = webViewClient
    }
}
