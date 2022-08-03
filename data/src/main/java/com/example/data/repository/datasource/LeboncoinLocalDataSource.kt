package com.example.data.repository.datasource

import com.example.data.model.AdvertEntity
import com.example.domain.model.Advert
import kotlinx.coroutines.flow.Flow

interface LeboncoinLocalDataSource {
    fun getAdvertsFromDB(): List<AdvertEntity>
    fun saveAdverts(advert: List<AdvertEntity>)

}