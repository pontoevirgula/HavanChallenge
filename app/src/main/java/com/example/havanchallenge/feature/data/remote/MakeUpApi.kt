package com.example.havanchallenge.feature.data.remote

import com.example.havanchallenge.feature.data.remote.modeldto.ProductDTO
import retrofit2.http.GET

interface MakeUpApi {

    @GET("api/v1/products.json/")
    suspend fun getProductList(): ProductDTO
}