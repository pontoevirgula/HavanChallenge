package com.example.havanchallenge.feature.presentation

sealed class Screen(val rout: String) {
    data object Home : Screen("home")
    data object Details : Screen("details")
}