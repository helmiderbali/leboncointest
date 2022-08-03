package com.example.leboncoin.di

import com.example.data.db.LeboncoinDao
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasourceimpl.LeboncoinLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(leboncoinDao: LeboncoinDao): LeboncoinLocalDataSource =
        LeboncoinLocalDataSourceImpl(leboncoinDao = leboncoinDao)
}
