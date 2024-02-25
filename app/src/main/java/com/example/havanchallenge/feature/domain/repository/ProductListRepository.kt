package com.example.havanchallenge.feature.domain.repository

import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.data.remote.modeldto.ProductDTO
import com.example.havanchallenge.feature.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductListRepository {

    suspend fun getProductList() : Flow<Resource<List<Product>>>

    suspend fun getProduct(id : Int) : Flow<Resource<ProductDTO.ProductDTOItem>>
}