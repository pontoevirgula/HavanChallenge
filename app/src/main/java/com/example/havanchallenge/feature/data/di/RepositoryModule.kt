package com.example.havanchallenge.feature.data.di

import com.example.havanchallenge.feature.data.repository.ProductListRepositoryImpl
import com.example.havanchallenge.feature.domain.repository.ProductListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repositoryImpl: ProductListRepositoryImpl
    ) : ProductListRepository
}