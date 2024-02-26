package com.example.havanchallenge.feature.domain.repository

import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun getFavorites() : Flow<Resource<List<Product>>>
    suspend fun insert(product: Product)
    suspend fun delete(product: Product)

}