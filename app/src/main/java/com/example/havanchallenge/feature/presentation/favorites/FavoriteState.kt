package com.example.havanchallenge.feature.presentation.favorites

import com.example.havanchallenge.feature.domain.model.Product

data class FavoriteState (
    val isLoading: Boolean = false,
    val favorites : List<Product>? = emptyList()
)