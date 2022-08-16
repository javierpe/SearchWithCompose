package com.app.ml.di

import com.app.ml.productDetail.data.repositories.ProductDetailRepository
import com.app.ml.searchProduct.data.repositories.SearchRepository
import com.app.ml.productDetail.data.api.useCases.ProductDetailUseCase
import com.app.ml.searchProduct.data.api.useCases.SearchUseCase
import com.app.ml.productDetail.domain.impl.ProductDetailRepositoryImpl
import com.app.ml.productDetail.domain.useCases.ProductDetailUseCaseImpl
import com.app.ml.searchProduct.domain.impl.SearchRepositoryImpl
import com.app.ml.searchProduct.domain.useCases.SearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindsSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    abstract fun bindsProductDetailRepository(
        impl: ProductDetailRepositoryImpl
    ): ProductDetailRepository

    @Binds
    abstract fun bindsSearchUseCase(
        impl: SearchUseCaseImpl
    ): SearchUseCase

    @Binds
    abstract fun bindsProductDetailApiUseCase(
        impl: ProductDetailUseCaseImpl
    ): ProductDetailUseCase
}