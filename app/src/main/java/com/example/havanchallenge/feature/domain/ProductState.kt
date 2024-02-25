package com.example.havanchallenge.feature.domain

import com.example.havanchallenge.feature.domain.model.Product

data class ProductState(
    val isLoading: Boolean = false,
    val products : List<Product>? = emptyList()
)
