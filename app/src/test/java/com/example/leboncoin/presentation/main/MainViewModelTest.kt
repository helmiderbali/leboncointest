package com.example.leboncoin.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.Advert
import com.example.domain.model.Response
import com.example.domain.model.ResponseStatus
import com.example.domain.repository.AdvertRepository
import com.example.domain.useCase.GetAdvertUseCase
import com.example.utils.TestCoroutineRule
import com.example.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


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

            viewModel.uiState.observeForever { }

            viewModel.getAdverts()

            withContext(Dispatchers.Main) {
                val result = viewModel.uiState.getOrAwaitValue(5)
                assert(result != null)
                assert(result.advertItems != null)
            }
        }
    }
}
