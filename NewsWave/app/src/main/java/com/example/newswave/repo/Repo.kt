package com.example.newswave.repo

import com.example.newswave.network.NewsModel
import com.example.newswave.network.RetrofitClient
import retrofit2.Response

class Repo {

    suspend fun fetchNewsFromApi() : Response<NewsModel> {
        return RetrofitClient.apiService.getEverything()
    }
}