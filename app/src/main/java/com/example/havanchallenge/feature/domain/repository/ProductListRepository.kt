package com.example.havanchallenge.feature.domain.repository

import com.example.havanchallenge.feature.data.remote.modeldto.ProductDTO

interface ProductListRepository {

    suspend fun getProductList() : List<ProductDTO>

}