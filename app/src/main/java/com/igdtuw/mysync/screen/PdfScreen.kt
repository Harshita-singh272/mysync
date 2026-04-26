package com.igdtuw.mysync.screen
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import java.io.File
import java.net.URL
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//
////import android.app.Activity
////import android.content.Context
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.viewinterop.AndroidView
////import java.io.File
////import java.net.URL
////import androidx.compose.runtime.Composable
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.viewinterop.AndroidView
////
////@Composable
////fun PdfScreen(url: String, page: Int) {
////
////    val context = LocalContext.current
////
////    AndroidView(
////        factory = { ctx ->
////            com.github.barteksc.pdfviewer.PDFView(ctx, null)
////        },
////        update = { pdfView ->
////
////            Thread {
////                try {
////                    val file = File(context.cacheDir, "timetable.pdf")
////
////                    val input = URL(url).openStream()
////                    val output = file.outputStream()
////
////                    input.copyTo(output)
////
////                    (context as Activity).runOnUiThread {
////                        pdfView.fromFile(file)
////                            .defaultPage(page - 1)
////                            .enableSwipe(true)
////                            .load()
////                    }
////
////                } catch (e: Exception) {
////                    e.printStackTrace()
////                }
////            }.start()
////        }
////    )
////}
//@Composable
//fun PdfScreen(url: String, page: Int) {
//
//    val context = LocalContext.current
//
//    var file by remember { mutableStateOf<File?>(null) }
//
//    LaunchedEffect(url) {
//        try {
//            val tempFile = File(context.cacheDir, "timetable.pdf")
//
//            withContext(Dispatchers.IO) {
//
//                val input = URL(url).openStream()
//                val output = tempFile.outputStream()
//
//                input.copyTo(output)
//            }
//
//            file = tempFile
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    // 🔥 UI
//    if (file == null) {
//        // Loading state
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Text("Loading PDF...")
//        }
//    } else {
//        AndroidView(
//            factory = { ctx ->
//                PDFView(ctx, null)
//            },
//            update = { pdfView ->
//                pdfView.fromFile(file!!)
//                    .defaultPage(page - 1)
//                    .enableSwipe(true)
//                    .load()
//            }
//        )
//    }
//}
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