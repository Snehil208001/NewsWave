package com.example.newswave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold // Make sure Scaffold is from material3
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface // Make sure Surface is from material3
import androidx.compose.ui.Modifier
import com.example.newswave.screen.Nav.NavApp
import com.example.newswave.ui.theme.NewsWaveTheme
// No need for import com.example.newswave.viewModels.NewsViewModel here
// No need for import androidx.activity.viewModels here

class MainActivity : ComponentActivity() {
    // REMOVE THIS LINE: private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsWaveTheme {
                // Using Surface is generally preferred for the root background in Compose apps
                // It ensures the background color is correctly applied based on the theme.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // Use theme's background color
                ) {
                    // Call NavApp without any parameters
                    NavApp() // THIS IS THE CORRECT CALL
                }
            }
        }
    }
}