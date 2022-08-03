package com.example.leboncoin.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.LeboncoinDB
import com.example.data.db.LeboncoinDB.Companion.DATABASE_NAME
import com.example.data.db.LeboncoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): LeboncoinDB =
        Room.databaseBuilder(app, LeboncoinDB::class.java, DATABASE_NAME).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(leboncoinDB: LeboncoinDB) : LeboncoinDao = leboncoinDB.getLeboncoinDao()

}