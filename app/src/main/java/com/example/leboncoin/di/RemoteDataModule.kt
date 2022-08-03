package com.example.leboncoin.di

import com.example.data.api.LeboncoinApi
import com.example.data.db.LeboncoinDB
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import com.example.data.repository.datasourceimpl.LeboncoinRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideLeboncoinRemoteDataSource(leboncoinApi: LeboncoinApi) : LeboncoinRemoteDataSource =
        LeboncoinRemoteDataSourceImpl(leboncoinApi)
}
