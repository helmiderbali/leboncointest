package com.example.data.repository

import androidx.annotation.WorkerThread
import com.example.data.model.AdvertEntity
import com.example.data.model.NetworkResponse
import com.example.data.model.NetworkState
import com.example.data.model.Status
import com.example.data.model.mapper.mapper.AdvertMapper
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import com.example.domain.model.Advert
import com.example.domain.model.Response
import com.example.domain.model.ResponseStatus
import com.example.domain.repository.AdvertRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdvertRepositoryImpl @Inject constructor(
    private val leboncoinLocalDataSource: LeboncoinLocalDataSource,
    private val leboncoinRemoteDataSource: LeboncoinRemoteDataSource
) : AdvertRepository {

    override suspend fun getAdverts(): Response<List<Advert>> {
        val response = leboncoinRemoteDataSource.getAdvertsFromRemote()
        return when (response.networkState.status) {
            Status.SUCCESS -> {
                val data = response.data

                data?.let {
                        leboncoinLocalDataSource.saveAdverts(it)
                }

                val result = mutableListOf<Advert>()

                data?.forEach { advert ->
                    result.add(AdvertMapper.fromEntity(advert))
                }

                return Response(ResponseStatus.SUCCESS, result)
            }
            Status.FAILED -> {
                val result = mutableListOf<Advert>()

                val data = leboncoinLocalDataSource.getAdvertsFromDB()
                if (data.isNotEmpty()) {
                    data.forEach { advert ->
                        result.add(AdvertMapper.fromEntity(advert))
                    }
                }

                Response(ResponseStatus.ERROR, result)
            }
        }
    }
}