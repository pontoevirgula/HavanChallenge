package com.example.havanchallenge.feature.domain.usecase

import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.data.remote.modeldto.toProductFromRemote
import com.example.havanchallenge.feature.domain.model.Product
import com.example.havanchallenge.feature.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val repository: ProductListRepository
): GetProductsUseCase {
    override fun fetchProducts(): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val getProductFromApi = repository.getProductList()
                if (getProductFromApi.isNotEmpty()) {
                    emit(Resource.Success(
                        data = getProductFromApi.take(30).map { it.toProductFromRemote() }
                    ))
                    emit(Resource.Loading(false))
                    return@flow
                } else {
                    emit(Resource.Error("Exception: Lista de produtos vazia ou nula"))
                    return@flow
                }
            } catch (e: IOException) {
                emit(Resource.Error("IO Exception: ${e.message}"))
                e.printStackTrace()
                return@flow
            } catch (e: TimeoutException) {
                emit(Resource.Error("Timeout Exception: ${e.message}"))
                e.printStackTrace()
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error("Http Exception: ${e.message}"))
                e.printStackTrace()
                return@flow
            }
        }
    }
}
interface GetProductsUseCase{
    fun fetchProducts() : Flow<Resource<List<Product>>>
}