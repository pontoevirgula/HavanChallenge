package com.example.havanchallenge.feature.data.repository

import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.data.remote.MakeUpApi
import com.example.havanchallenge.feature.data.remote.modeldto.ProductDTO
import com.example.havanchallenge.feature.domain.model.Product
import com.example.havanchallenge.feature.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val api: MakeUpApi
) : ProductListRepository {
    override suspend fun getProductList(): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading(true))

            try {
                val getProductFromApi = api.getProductList().take(10)
                emit(Resource.Success(
                    data = getProductFromApi.map { it.toProductFromRemote() }
                ))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Error(message = "Error ao carregar produtos"))
                return@flow
            }
        }
    }

    override suspend fun getProduct(id: Int): Flow<Resource<ProductDTO.ProductDTOItem>> {
        TODO("Not yet implemented")
    }
}