package com.example.havanchallenge.feature.domain.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id : Int,
    val brand : String,
    val description : String,
    val price : String,
    val rating : Double,
    val productType: String,
    val category: String
): Parcelable


class ProductType : NavType<Product>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Product? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): Product {
        return Gson().fromJson(value, Product::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: Product) {
        bundle.putParcelable(key, value)
    }
}