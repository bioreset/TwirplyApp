package com.dariusz.twirplyapp.presentation.components.common

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch

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

    val currentCoroutineScope = rememberCoroutineScope()

    AndroidView({ webView }) { webViewX ->
        currentCoroutineScope.launch {
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
    return remember {
        WebViewClient()
    }
}
