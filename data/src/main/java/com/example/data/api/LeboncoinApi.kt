package com.example.data.api

import com.example.data.model.AdvertEntity
import retrofit2.Response
import retrofit2.http.GET

interface LeboncoinApi {
    @GET("img/shared/technical-test.json")
    suspend fun getAdverts(): Response<List<AdvertEntity>>
}