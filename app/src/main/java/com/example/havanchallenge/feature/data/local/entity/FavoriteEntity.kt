package com.example.havanchallenge.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.havanchallenge.feature.domain.model.Product

@Entity
data class FavoriteEntity (
    @PrimaryKey val id : Int,
    val brand : String,
    val description : String,
    val price : String,
    val rating : Double,
    val productType: String,
    val category: String
)
    fun List<FavoriteEntity>.toProductFromDb() = map {
        Product(
            brand = it.brand,
            description = it.description,
            price = it.price,
            rating = it.rating,
            productType = it.productType,
            category = it.category,
            id = it.id
        )
    }

