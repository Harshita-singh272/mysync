package com.igdtuw.mysync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.igdtuw.mysync.screen.PdfScreen

class PdfActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("url") ?: ""
        val page = intent.getIntExtra("page", 0)

        setContent {
            PdfScreen(url, page)
        }
    }
}