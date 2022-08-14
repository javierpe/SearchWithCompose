package com.app.ml.di

import com.app.ml.api.repositories.ProductDetailRepository
import com.app.ml.api.repositories.SearchRepository
import com.app.ml.api.useCases.ProductDetailUseCase
import com.app.ml.api.useCases.SearchUseCase
import com.app.ml.impl.ProductDetailRepositoryImpl
import com.app.ml.impl.ProductDetailUseCaseImpl
import com.app.ml.impl.SearchRepositoryImpl
import com.app.ml.impl.SearchUseCaseImpl
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