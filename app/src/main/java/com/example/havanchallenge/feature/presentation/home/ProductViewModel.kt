package com.example.havanchallenge.feature.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.feature.domain.ProductState
import com.example.havanchallenge.feature.domain.repository.ProductListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductListRepository
) : ViewModel() {

    private var _productListState = MutableStateFlow(ProductState())
    val productListState = _productListState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _productListState.update {
                it.copy(isLoading = true)
            }
            repository.getProductList().collectLatest { result ->
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
                                    products = productList
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}