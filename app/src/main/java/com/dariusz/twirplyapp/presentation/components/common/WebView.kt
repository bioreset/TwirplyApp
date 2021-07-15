package com.dariusz.twirplyapp.presentation.components.common

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

val linkToLogIn = ""

@Composable
fun OpenLink() {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(linkToLogIn)
        }
    }, update = {
        it.loadUrl(linkToLogIn)
    }
    )
}