package com.example.data.repository.datasource

import com.example.data.model.AdvertEntity
import com.example.data.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface LeboncoinRemoteDataSource {
    suspend fun getAdvertsFromRemote(): NetworkResponse<List<AdvertEntity>>
}