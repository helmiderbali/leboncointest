package com.example.data.repository.datasourceimpl

import android.util.Log
import com.example.data.api.LeboncoinApi
import com.example.data.model.AdvertEntity
import com.example.data.model.NetworkState
import com.example.data.model.NetworkResponse
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeboncoinRemoteDataSourceImpl  @Inject constructor(
    val leboncoinApi: LeboncoinApi,
) : LeboncoinRemoteDataSource {
    override suspend fun getAdvertsFromRemote(): NetworkResponse<List<AdvertEntity>> {
        return try {
            val data = leboncoinApi.getAdverts().body()
            NetworkResponse(NetworkState.SUCCESS, data)
        } catch (e: Exception) {
            NetworkResponse(NetworkState.ERROR)
        }
    }
}