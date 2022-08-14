package com.app.ml.di

import com.app.ml.api.controllers.NavigationController
import com.app.ml.impl.NavigationControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindNavigationControllerService(
        impl: NavigationControllerImpl
    ): NavigationController
}