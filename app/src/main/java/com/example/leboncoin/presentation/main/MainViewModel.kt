package com.example.leboncoin.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Advert
import com.example.domain.model.Status
import com.example.domain.useCase.GetAdvertUseCase
import com.example.leboncoin.presentation.model.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAdvertUseCase: GetAdvertUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<MainUiState<List<Advert>>>()
    val uiState: MutableLiveData<MainUiState<List<Advert>>>
        get() = _uiState

    init {
        getAdverts()
    }

    fun getAdverts() {
        viewModelScope.launch() {
            getAdvertUseCase.invoke().let {
                when (it.responseState.status) {
                    Status.SUCCESS -> _uiState.postValue(MainUiState(advertItems = it.data))
                    Status.FAILED -> _uiState.postValue(
                        MainUiState(error = "something went wrong", advertItems = it.data)
                    )
                }
            }
        }
    }
}