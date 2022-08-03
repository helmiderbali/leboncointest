package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.AdvertEntity

@Database(entities = [AdvertEntity::class], version = 1)
abstract class LeboncoinDB: RoomDatabase() {

    abstract fun getLeboncoinDao(): LeboncoinDao

    companion object {
        const val DATABASE_NAME ="LeboncoinDatabase.db"
    }
}