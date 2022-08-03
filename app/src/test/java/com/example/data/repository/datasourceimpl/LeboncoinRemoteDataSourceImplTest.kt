package com.example.data.repository.datasourceimpl

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.api.LeboncoinApi
import com.example.data.model.AdvertEntity
import com.example.data.model.NetworkResponse
import com.example.data.model.NetworkState
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import com.example.domain.model.Advert
import com.example.domain.model.ResponseStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import java.lang.Exception

@RunWith(JUnit4::class)
class LeboncoinRemoteDataSourceImplTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @MockK
    lateinit var leboncoinApi: LeboncoinApi

    lateinit var leboncoinRemoteDataSource: LeboncoinRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        leboncoinRemoteDataSource = LeboncoinRemoteDataSourceImpl(leboncoinApi)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRemoteDataSuccess() {
        runTest {

            coEvery {
                leboncoinApi.getAdverts()
            } returns Response.success(listOf())

            val result = leboncoinRemoteDataSource.getAdvertsFromRemote()

            assert(result.data != null)
            assert(result.networkState == NetworkState.SUCCESS)
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRemoteDataError() {
        runTest {

            coEvery {
                leboncoinApi.getAdverts()
            }.throws(Exception())

            val result = leboncoinRemoteDataSource.getAdvertsFromRemote()

            assert(result.data == null)
            assert(result.networkState == NetworkState.ERROR)
        }
    }
}