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

//    fun a() : List<Product> =
//        listOf( Product(
//            id=1044,
//            brand="boosh",
//            description="All of our products are free from lead and heavy metals, parabens, phthalates, artificial colourants, and synthetic fragrances.  Boosh lipstick glides on smoothly for clean & protective SPF coverage. They are filled with hydrating oils and butters to preserve and enhance your lips natural surface. Organic sweet orange oil gives a light and cheerful scent.",
//            price="26.0",
//            rating=0.0,
//            productType="",
//            category="lipstick")
//        )


    override suspend fun insert(product: Product) {
        dao.insertFavorite(product.toEntityDb())
        Resource.Success(data = product)
    }

    override suspend fun delete(product: Product) {
        dao.deleteFavoriteById(product.toEntityDb())
        Resource.Success(product)
    }
}