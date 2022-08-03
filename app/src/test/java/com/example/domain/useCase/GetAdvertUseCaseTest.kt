package com.example.domain.useCase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.Advert
import com.example.domain.model.Response
import com.example.domain.model.ResponseStatus
import com.example.domain.repository.AdvertRepository
import com.example.leboncoin.presentation.main.MainViewModel
import com.example.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetAdvertUseCaseTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @MockK
    lateinit var advertRepository: AdvertRepository

    lateinit var getAdvertUseCase: GetAdvertUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAdvertUseCase = GetAdvertUseCase(advertRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInvoke() {

        runTest {

            coEvery {
                advertRepository.getAdverts()
            } returns Response(
                ResponseStatus.SUCCESS,
                listOf<Advert>()
            )

            val result = getAdvertUseCase.invoke()
            assert(result.data != null)
            assert(result.responseState == ResponseStatus.SUCCESS)
        }
    }
}