package com.example.havanchallenge.feature.presentation.home

import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.domain.model.Product
import com.example.havanchallenge.feature.domain.usecase.GetOrderListUseCase
import com.example.havanchallenge.feature.domain.usecase.GetProductsUseCase
import com.example.havanchallenge.feature.presentation.util.ViewModelRule
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    val dispatcherRule = ViewModelRule()

    @get:Rule
    val mockKRule = MockKRule(this)

    private lateinit var viewModel: ProductViewModel

    private val fakeUseCase: GetProductsUseCase = mockk()

    private val fakeOrderListUseCase: GetOrderListUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = ProductViewModel(fakeUseCase, fakeOrderListUseCase)
    }

    @Test
    fun `for success resource, data must be available`() = runTest {
        val productState = viewModel.productListState.value

        assertTrue(!productState.products.isNullOrEmpty())
        assertTrue(!productState.products?.first()?.brand.isNullOrEmpty())
        assertFalse(productState.products?.first()?.id == 0)
        assert(productState.products?.first()?.description != "")
        assertEquals(false, productState.isLoading)
    }

    @Test
    fun `Should validate data`() = runTest {
        whenever(fakeUseCase.fetchProducts()).thenReturn(flowOf(mockProducts()))
        val result = viewModel.productListState.value.products
        assertNotNull(result?.first())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `for success resource, data must be available 2`() = runTest {
        viewModel.getProducts()

        val result = coEvery {
            fakeUseCase.fetchProducts()
        } returns flow { mockProducts2() }

        assertNotNull(result)

    }

    private fun mockProducts(): Resource<List<Product>> =
        Resource.Success(
            listOf(
                Product(
                    id = 1,
                    brand = "hangloose",
                    description = "muito boa",
                    price = "R$ 1,00",
                    rating = 4.5,
                    productType = "teste",
                    category = "cara"
                )
            )
        )

    private fun mockProducts2(): Flow<Resource<List<Product>>> = flow {
        Resource.Success(
            listOf(
                Product(
                    id = 1,
                    brand = "hangloose",
                    description = "muito boa",
                    price = "R$ 1,00",
                    rating = 4.5,
                    productType = "teste",
                    category = "cara"
                )
            )
        )
    }

}

