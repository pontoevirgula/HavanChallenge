package com.example.havanchallenge.feature.data.remote.modeldto


import com.example.havanchallenge.feature.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ProductDTO : ArrayList<ProductDTO.ProductDTOItem>() {
    @Serializable
    data class ProductDTOItem(
        @SerialName("api_featured_image")
        val apiFeaturedImage: String,
        @SerialName("brand")
        val brand: String,
        @SerialName("category")
        val category: String,
        @SerialName("created_at")
        val createdAt: String,
        @SerialName("currency")
        val currency: String,
        @SerialName("description")
        val description: String? = "",
        @SerialName("id")
        val id: Int,
        @SerialName("image_link")
        val imageLink: String,
        @SerialName("name")
        val name: String,
        @SerialName("price")
        val price: String? = "0,00",
        @SerialName("price_sign")
        val priceSign: String,
        @SerialName("product_api_url")
        val productApiUrl: String,
        @SerialName("product_colors")
        val productColors: List<ProductColor>,
        @SerialName("product_link")
        val productLink: String,
        @SerialName("product_type")
        val productType: String? = "",
        @SerialName("rating")
        val rating: Double,
        @SerialName("tag_list")
        val tagList: List<String>,
        @SerialName("updated_at")
        val updatedAt: String,
        @SerialName("website_link")
        val websiteLink: String
    ) {
        fun toProductFromRemote(): Product =
            Product(
                brand = brand,
                description = description ?: "",
                price = price ?: "",
                rating = rating,
                productType = productType ?: "",
                category = category,
                id = id
            )
    }

    @Serializable
    data class ProductColor(
        @SerialName("colour_name")
        val colourName: String,
        @SerialName("hex_value")
        val hexValue: String
    )

}