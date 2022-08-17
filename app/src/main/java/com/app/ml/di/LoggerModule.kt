package com.app.ml.di

import com.app.ml.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = Logger()
}