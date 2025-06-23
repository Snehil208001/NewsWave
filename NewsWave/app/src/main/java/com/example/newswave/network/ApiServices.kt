package com.example.newswave.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String = "tesla",
        @Query("from") from: String = "2025-06-21", // UPDATED date
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "daa19f5ce3804218b9a814f5a0e7e7bd"
    ): Response<NewsModel>
}



//daa19f5ce3804218b9a814f5a0e7e7bd