package com.example.leboncoin.presentation.model

import com.example.domain.model.Advert

data class MainUiState<T>(
    val error: String? = null,
    val isLoading: Boolean = false,
    val advertItems: List<Advert>? = null
)
