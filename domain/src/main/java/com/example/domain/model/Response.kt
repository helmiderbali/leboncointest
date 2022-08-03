package com.example.domain.model

data class Response<T>(
    val responseState: ResponseStatus,
    val data: T? = null
)