package com.example.data.model.mapper.mapper

import com.example.data.model.AdvertEntity
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AdvertMapperTest {

    lateinit var advertEntity: AdvertEntity
    @Before
    fun setUp() {
        advertEntity = AdvertEntity(1, 2, 3 , "thumbnailurl", "title","url")
    }

    @Test
    fun testMappingWorks() {

       val result =  AdvertMapper.fromEntity(advertEntity)
        assert(result.id == advertEntity.id)
        assert(result.albumId == advertEntity.albumId)
        assert(result.title == advertEntity.title)
        assert(result.thumbnailUrl == advertEntity.thumbnailUrl)
        assert(result.url == advertEntity.url)

    }
}