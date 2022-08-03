package com.example.data.repository

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdvertRepositoryImpl @Inject constructor(
    private val leboncoinLocalDataSource: LeboncoinLocalDataSource,
    private val leboncoinRemoteDataSource: LeboncoinRemoteDataSource
) : AdvertRepository {

    override suspend fun getAdverts(): Response<List<Advert>> {
        val response = leboncoinRemoteDataSource.getAdvertsFromRemote()
        val result = mutableListOf<Advert>()

        return when (response.networkState.status) {
            Status.SUCCESS -> {
                val data = response.data

                data?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        leboncoinLocalDataSource.saveAdverts(it)
                    }
                }

                data?.forEach { advert ->
                    result.add(AdvertMapper.fromEntity(advert))
                }

                return Response(ResponseStatus.SUCCESS, result)
            }
            Status.FAILED -> {

                val dbResult =
                    withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                        val data = leboncoinLocalDataSource.getAdvertsFromDB()
                        if (data.isNotEmpty()) {
                            data.forEach { advert ->
                                result.add(AdvertMapper.fromEntity(advert))
                            }
                        }

                        result
                    }

                Response(ResponseStatus.ERROR, dbResult)
            }
        }
    }
}