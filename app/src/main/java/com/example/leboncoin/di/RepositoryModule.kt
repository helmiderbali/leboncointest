package com.example.leboncoin.di

import com.example.data.repository.AdvertRepositoryImpl
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import com.example.domain.repository.AdvertRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLeboncoinRepository(
        leboncoinRemoteDataSource: LeboncoinRemoteDataSource,
        leboncoinLocalDataSource: LeboncoinLocalDataSource
    ): AdvertRepository =
        AdvertRepositoryImpl(leboncoinLocalDataSource, leboncoinRemoteDataSource)
}
