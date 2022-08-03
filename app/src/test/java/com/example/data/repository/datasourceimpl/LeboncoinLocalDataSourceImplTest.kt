package com.example.data.repository.datasourceimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.db.LeboncoinDao
import com.example.data.model.NetworkState
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import com.example.data.repository.datasource.LeboncoinRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class LeboncoinLocalDataSourceImplTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @MockK
    lateinit var leboncoinDao: LeboncoinDao

    lateinit var leboncoinLocalDataSource: LeboncoinLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        leboncoinLocalDataSource = LeboncoinLocalDataSourceImpl(leboncoinDao)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetAdverts() {
        runTest {

            coEvery {
                leboncoinDao.getAll()
            } returns listOf()

            val result = leboncoinLocalDataSource.getAdvertsFromDB()

            assert(result != null)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSaveAdvert() {
        runTest {

            coEvery {
                leboncoinDao.insertAll(listOf())
            } returns Unit

            leboncoinLocalDataSource.saveAdverts(listOf())

            coVerify { leboncoinDao.insertAll(listOf()) }
        }
    }
}