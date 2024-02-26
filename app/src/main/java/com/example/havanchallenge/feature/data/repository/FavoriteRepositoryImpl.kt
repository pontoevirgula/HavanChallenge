package com.example.havanchallenge.feature.data.repository

import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.data.local.db.FavoriteDao
import com.example.havanchallenge.feature.data.local.entity.toProductFromDb
import com.example.havanchallenge.feature.domain.model.Product
import com.example.havanchallenge.feature.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoriteRepository{
    override suspend fun getFavorites(): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val favorites = dao.listFavorites().toProductFromDb()

                emit( Resource.Success(data = favorites))
                emit(Resource.Loading(false))
                return@flow
            } catch (e : Exception){
                e.printStackTrace()
                emit(Resource.Error(message = "Error ao carregar favoritos"))
                return@flow
            }
        }
    }

    override suspend fun insert(product: Product) {
        dao.insertFavorite(product.toEntityDb())
        Resource.Success(data = product)
    }

    override suspend fun delete(product: Product) {
        dao.deleteFavoriteById(product.toEntityDb())
        Resource.Success(product)
    }
}