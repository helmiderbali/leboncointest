package com.example.leboncoin.di

import com.example.domain.repository.AdvertRepository
import com.example.domain.useCase.GetAdvertUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAdvertUseCases(advertRepository: AdvertRepository) =
        GetAdvertUseCase(advertRepository)

}
