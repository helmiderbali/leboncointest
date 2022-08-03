package com.example.data.model

data class NetworkResponse<T>(
    val networkState: NetworkState,
    val data: T? = null
)