package com.example.havanchallenge.feature.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.core.util.ViewUtil
import com.example.havanchallenge.feature.domain.ProductState
import com.example.havanchallenge.feature.domain.usecase.GetOrderListUseCase
import com.example.havanchallenge.feature.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase,
    private val orderListUseCase : GetOrderListUseCase
) : ViewModel() {

    private var _productListState = MutableStateFlow(ProductState())
    val productListState = _productListState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _productListState.update {
                it.copy(isLoading = true)
            }
            productsUseCase.fetchProducts().collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        _productListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _productListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let{ productList ->
                            _productListState.update {
                                it.copy(
                                    isLoading = false,
                                    products = orderListUseCase.orderList(productList, ViewUtil.oderTypeSelected)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}