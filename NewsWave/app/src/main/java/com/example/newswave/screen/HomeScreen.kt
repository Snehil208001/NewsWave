package com.example.newswave.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.newswave.screen.Nav.Routes
import com.example.newswave.viewModels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NewsViewModel, navController: NavController) {
    val articles = viewModel.res.value?.articles ?: emptyList()
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NewsWave") },
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
            contentAlignment = Alignment.Center // Center content in the Box
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (errorMessage != null) {
                // Display error message if there's an error
                Text(
                    text = "Failed to load news: $errorMessage",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            } else if (articles.isEmpty()) {
                // Display message if no articles are found after loading
                Text(
                    text = "No news articles available.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // Display articles list
                LazyColumn {
                    items(articles) { article ->
                        // Pass the article URL for navigation
                        EachCard(
                            onClick = {
                                article?.url?.let { url ->
                                    navController.navigate(Routes.DetailScreenRoutes(url))
                                } ?: run {
                                    println("Article URL is null, cannot navigate.")
                                }
                            },
                            title = article?.title,
                            imageUrl = article?.urlToImage
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EachCard(
    onClick: () -> Unit,
    title: String?,
    imageUrl: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp) // Adjusted padding
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Fixed height for consistent card size
                .padding(8.dp)
        ) {
            if (!imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "News Image",
                    modifier = Modifier
                        .width(100.dp)
                        .height(80.dp), // Image size slightly smaller than row height
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Placeholder Image",
                    modifier = Modifier
                        .width(100.dp)
                        .height(80.dp),
                    alignment = Alignment.Center, // Center placeholder image
                )
            }

            Spacer(modifier = Modifier.width(12.dp)) // Increased space between image and text

            Column(
                modifier = Modifier
                    .weight(1f), // Takes remaining space
                verticalArrangement = Arrangement.Center // Vertically center text
            ) {
                Text(
                    text = title ?: "No Title Available",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium, // Use Material 3 typography
                    maxLines = 3, // Limit title to 3 lines
                    overflow = TextOverflow.Ellipsis // Add ellipsis if text overflows
                )
            }
        }
    }
}