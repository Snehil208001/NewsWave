package com.example.newswave.screen.Nav

import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    object MainScreenRoutes

    @Serializable
    data class DetailScreenRoutes(val articleUrl: String?)
}