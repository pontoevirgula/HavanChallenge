package com.example.havanchallenge.feature.data.repository

import com.example.havanchallenge.feature.data.remote.MakeUpApi
import com.example.havanchallenge.feature.data.remote.modeldto.ProductDTO
import com.example.havanchallenge.feature.domain.repository.ProductListRepository
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val api: MakeUpApi
) : ProductListRepository {
    override suspend fun getProductList(): List<ProductDTO> =
       api.getProductList()
}