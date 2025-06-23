package com.example.newswave.viewModels

import android.util.Log // Added for logging
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newswave.network.NewsModel
import com.example.newswave.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay // Import delay
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {

    val res = mutableStateOf<NewsModel?>(null)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null) // New state for error messages

    val repo = Repo()

    init {
        fetchdata()
    }

    fun fetchdata() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            errorMessage.value = null // Clear any previous error
            try {
                delay(2000) // Keep for testing UI. REMOVE IN PRODUCTION!
                val response = repo.fetchNewsFromApi()
                if (response.isSuccessful) {
                    res.value = response.body()
                    Log.d("NewsWave", "Data fetched successfully. Articles: ${res.value?.articles?.size}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage.value = "Error: ${response.code()} - ${errorBody ?: "Unknown error"}"
                    Log.e("NewsWave", "API Error: ${response.code()} - $errorBody")
                }
            } catch (e: Exception) {
                errorMessage.value = "Network error: ${e.localizedMessage ?: "Unknown error"}"
                Log.e("NewsWave", "Network error during fetch", e)
            } finally {
                isLoading.value = false
                Log.d("NewsWave", "Loading finished.")
            }
        }
    }
}