package com.example.havanchallenge.feature.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havanchallenge.core.util.Resource
import com.example.havanchallenge.core.util.ViewUtil
import com.example.havanchallenge.core.util.ViewUtil.A_TO_Z
import com.example.havanchallenge.core.util.ViewUtil.HIGH_PRICE
import com.example.havanchallenge.core.util.ViewUtil.LOW_PRICE
import com.example.havanchallenge.core.util.ViewUtil.Z_TO_A
import com.example.havanchallenge.feature.domain.ProductState
import com.example.havanchallenge.feature.domain.model.Product
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
                                    products = orderList(productList, ViewUtil.oderTypeSelected)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun orderList(productList: List<Product>, oderTypeSelected: Int): List<Product> {
        return when(oderTypeSelected){
            LOW_PRICE ->{
                productList.sortedBy { it.price }
            }
             HIGH_PRICE ->{
                productList.sortedBy { it.price }.reversed()
            }
             A_TO_Z ->{
                 productList.sortedBy { it.brand }
            }
             Z_TO_A ->{
                 productList.sortedBy { it.brand }.reversed()
            }
            else -> {
                productList
            }
        }
    }
}