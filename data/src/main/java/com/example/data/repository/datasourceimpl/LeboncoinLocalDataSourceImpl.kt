package com.example.data.repository.datasourceimpl

import com.example.data.db.LeboncoinDao
import com.example.data.model.AdvertEntity
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import javax.inject.Inject

class LeboncoinLocalDataSourceImpl @Inject constructor(val leboncoinDao: LeboncoinDao) :
    LeboncoinLocalDataSource {

    override fun getAdvertsFromDB(): List<AdvertEntity> {
        return leboncoinDao.getAll()
    }

    override fun saveAdverts(adverts: List<AdvertEntity>) {
        leboncoinDao.insertAll(adverts)
    }
}