package com.igdtuw.mysync.screen

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PdfScreen(url: String, page: Int) {

    val context = LocalContext.current

    val fullUrl = "https://docs.google.com/gview?embedded=true&url=$url"

    AndroidView(
        factory = {
            android.webkit.WebView(it).apply {
                settings.javaScriptEnabled = true
                loadUrl(fullUrl)
            }
        }
    )
}