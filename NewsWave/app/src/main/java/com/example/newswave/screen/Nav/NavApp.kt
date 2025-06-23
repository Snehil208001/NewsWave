package com.example.newswave.screen.Nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel // For getting ViewModel in NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newswave.screen.DetailScreen
import com.example.newswave.screen.HomeScreen
import com.example.newswave.viewModels.NewsViewModel

@Composable
fun NavApp(){ // Removed viewModel param here, will inject via Hilt or viewModel()
    val navController = rememberNavController()
    // Get the ViewModel here, so it's scoped to the NavHost or Activity
    val newsViewModel: NewsViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.MainScreenRoutes){

        composable<Routes.MainScreenRoutes> {
            HomeScreen(
                viewModel = newsViewModel, // Pass the ViewModel
                navController = navController // Pass navController for navigation
            )
        }
        // Use arguments for DetailScreenRoutes
        composable<Routes.DetailScreenRoutes> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.DetailScreenRoutes>()
            DetailScreen(articleUrl = args.articleUrl)
        }
    }
}