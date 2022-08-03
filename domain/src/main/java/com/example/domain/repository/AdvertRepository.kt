package com.example.domain.repository

import com.example.domain.model.Advert
import com.example.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface AdvertRepository {
    suspend fun getAdverts() : Response<List<Advert>>
}