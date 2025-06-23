package com.example.newswave.screen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView // Import AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(articleUrl: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Article Detail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (!articleUrl.isNullOrEmpty()) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true // Enable JavaScript for rich content
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                                    // This line is crucial: it tells the WebView to load the URL
                                    // itself instead of deferring to the system's default browser.
                                    url?.let { view?.loadUrl(it) }
                                    return true // Indicate that the WebView handled the URL
                                }
                            }
                            loadUrl(articleUrl)
                        }
                    },
                    update = { webView ->
                        // You can add logic here to update the WebView if articleUrl changes
                        // (though for this specific use case, it's typically loaded once)
                        // If the URL is different, load it again.
                        if (webView.url != articleUrl) {
                            articleUrl?.let { webView.loadUrl(it) }
                        }
                    }
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "No URL available for this article.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}