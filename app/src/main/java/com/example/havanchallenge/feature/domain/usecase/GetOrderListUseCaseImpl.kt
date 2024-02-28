package com.example.havanchallenge.feature.domain.usecase

import com.example.havanchallenge.core.util.ViewUtil
import com.example.havanchallenge.feature.domain.model.Product
import javax.inject.Inject

class GetOrderListUseCaseImpl @Inject constructor() : GetOrderListUseCase {
    override fun orderList(productList: List<Product>, oderTypeSelected: Int): List<Product> {
        return when(oderTypeSelected){
            ViewUtil.LOW_PRICE ->{
                productList.sortedBy { it.price }
            }
            ViewUtil.HIGH_PRICE ->{
                productList.sortedBy { it.price }.reversed()
            }
            ViewUtil.A_TO_Z ->{
                productList.sortedBy { it.brand }
            }
            ViewUtil.Z_TO_A ->{
                productList.sortedBy { it.brand }.reversed()
            }
            else -> {
                productList
            }
        }
    }
}

interface GetOrderListUseCase{
    fun orderList(productList: List<Product>, oderTypeSelected: Int): List<Product>
}


