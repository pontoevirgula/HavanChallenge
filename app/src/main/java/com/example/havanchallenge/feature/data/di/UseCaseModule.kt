package com.example.havanchallenge.feature.data.di

import com.example.havanchallenge.feature.domain.usecase.GetOrderListUseCase
import com.example.havanchallenge.feature.domain.usecase.GetOrderListUseCaseImpl
import com.example.havanchallenge.feature.domain.usecase.GetProductsUseCase
import com.example.havanchallenge.feature.domain.usecase.GetProductsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {


    @Binds
    @Singleton
    abstract fun bindProductUseCase(
        useCaseImpl: GetProductsUseCaseImpl
    ) : GetProductsUseCase

    @Binds
    @Singleton
    abstract fun bindOrderListUseCase(
        useCaseImpl: GetOrderListUseCaseImpl
    ) : GetOrderListUseCase
}