package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.AdvertEntity

@Dao
interface LeboncoinDao {
    @Query("SELECT * FROM adverts")
    fun getAll(): List<AdvertEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<AdvertEntity>)

    @Query("DELETE FROM adverts")
    fun deleteAll()

}