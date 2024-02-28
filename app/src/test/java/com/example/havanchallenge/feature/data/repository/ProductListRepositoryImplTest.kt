package com.example.havanchallenge.feature.data.repository

import com.example.havanchallenge.feature.data.remote.MakeUpApi
import com.example.havanchallenge.feature.data.remote.modeldto.ProductDTO
import com.example.havanchallenge.feature.presentation.util.ViewModelRule
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductListRepositoryImplTest {
    @get:Rule
    val dispatcherRule = ViewModelRule()

    @get:Rule
    val mockKRule = MockKRule(this)


    private val api: MakeUpApi = mockk()

    private lateinit var repositoryImpl: ProductListRepositoryImpl

    @Before
    fun setup() {
        repositoryImpl = ProductListRepositoryImpl(api)
    }


    @Test
    fun `should validate return success from repository`() = runTest {
        val expected = fakeProductDTO()

        coEvery {
            api.getProductList()
        } returns fakeProductDTO()

        val result = repositoryImpl.getProductList()

        assertEquals(result, expected)
    }


    private fun fakeProductDTO(): List<ProductDTO> =
        listOf(
            ProductDTO(
                apiFeaturedImage = "//s3.amazonaws.com/donovanbailey/products/api_featured_images/000/001/048/original/open-uri20180708-4-13okqci?1531093614",
                id = 233,
                brand = "colourpop",
                category = "pencil",
                createdAt = "2018-07-08T23:45:08.056Z",
                currency = "CAD",
                description = "Lippie Pencil A long-wearing and high-intensity lip pencil that glides on easily and prevents feathering. Many of our Lippie Stix have a coordinating Lippie Pencil designed to compliment it perfectly, but feel free to mix and match!",
                imageLink = "https://cdn.shopify.com/s/files/1/1338/0845/collections/lippie-pencil_grande.jpg?v=1512588769",
                name = "Lippie Pencil",
                price = "5.0",
                priceSign = "$",
                productApiUrl = "https://makeup-api.herokuapp.com/api/v1/products/1048.json",
                productColors = emptyList(),
                productLink = "https://colourpop.com/collections/lippie-pencil",
                productType = "lip_liner",
                rating = 0.0,
                tagList = emptyList(),
                updatedAt = "2018-07-09T00:53:23.301Z",
                websiteLink = "https://colourpop.com",
            )
        )


}