package com.example.leboncoin.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.model.Advert
import com.example.domain.model.Response
import com.example.domain.model.ResponseStatus
import com.example.domain.repository.AdvertRepository
import com.example.domain.useCase.GetAdvertUseCase
import com.example.leboncoin.presentation.model.MainUiState
import com.example.utils.TestCoroutineRule
import com.example.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()


    private lateinit var viewModel: MainViewModel

    @MockK
    lateinit var getAdvertUseCase: GetAdvertUseCase

    @MockK
    lateinit var advertRepository: AdvertRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = MainViewModel(getAdvertUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAdvert() {

        runTest {

            coEvery {
                advertRepository.getAdverts()
            } returns Response(
                ResponseStatus.SUCCESS,
                listOf<Advert>()
            )

            coEvery {
                getAdvertUseCase.invoke()
            } returns Response(
                ResponseStatus.SUCCESS,
                listOf<Advert>()
            )

            viewModel.uiState.observeForever {  }

            viewModel.getAdverts()

            val result = viewModel.uiState.getOrAwaitValue()
            assert(result != null)
            assert(result.advertItems != null)


        }
    }
}
