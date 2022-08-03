package com.example.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.model.AdvertEntity
import com.example.data.model.NetworkResponse
import com.example.data.model.NetworkState
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import com.example.domain.model.Advert
import com.example.domain.model.Response
import com.example.domain.model.ResponseStatus
import com.example.domain.repository.AdvertRepository
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
class AdvertRepositoryImplTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @MockK
    lateinit var leboncoinLocalDataSource: LeboncoinLocalDataSource

    @MockK
    lateinit var leboncoinRemoteDataSource: LeboncoinRemoteDataSource

    lateinit var advertRepositoryImpl: AdvertRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        advertRepositoryImpl =
            AdvertRepositoryImpl(leboncoinLocalDataSource, leboncoinRemoteDataSource)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAdvertFromRemoteDS() {
        runTest {

            coEvery {
                leboncoinRemoteDataSource.getAdvertsFromRemote()
            } returns NetworkResponse<List<AdvertEntity>>(
                NetworkState.SUCCESS,
                listOf<AdvertEntity>()
            )

            coEvery {
                leboncoinLocalDataSource.saveAdverts(any())
            } returns Unit

            val result = advertRepositoryImpl.getAdverts()
            assert(result.data != null)
            assert(result.responseState == ResponseStatus.SUCCESS)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAdvertFromLocalDS() {
        runTest {

            coEvery {
                leboncoinRemoteDataSource.getAdvertsFromRemote()
            } returns NetworkResponse<List<AdvertEntity>>(
                NetworkState.ERROR,
                null
            )

            coEvery {
                leboncoinLocalDataSource.getAdvertsFromDB()
            } returns listOf()

            coEvery {
                leboncoinLocalDataSource.saveAdverts(any())
            } returns Unit

            val result = advertRepositoryImpl.getAdverts()
            assert(result.data != null)
            assert(result.responseState == ResponseStatus.ERROR)
        }

    }
}