package com.example.havanchallenge.feature.util

sealed class Screen(val rout: String) {
    data object Home : Screen("home")
    data object Details : Screen("details")

    data object Favorites : Screen("favorites")
}