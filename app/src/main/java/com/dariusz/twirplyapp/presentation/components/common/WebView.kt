package com.dariusz.twirplyapp.presentation.components.common

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun OpenLink(url: String) {
    val webView = rememberWebView()
    val webViewClient = rememberWebViewClient()
    WebViewContainer(url, webView, webViewClient)
}

@Composable
fun WebViewContainer(
    url: String,
    webView: WebView,
    webViewClient: WebViewClient
) {

    Column(modifier = Modifier.fillMaxSize()) {
        AndroidView({ webView }) { webViewX ->
            webViewX.webViewClient = webViewClient
            webViewX.loadUrl(url)
        }
    }
}

@Composable
fun rememberWebView(): WebView {
    val currentContext = LocalContext.current
    val webView = remember {
        WebView(currentContext)
    }
    webView.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    return webView
}

@Composable
fun rememberWebViewClient(): WebViewClient {
    val webViewClient = remember {
        WebViewClient()
    }
    return webViewClient
}

