package com.example.data.repository.datasourceimpl

import com.example.data.db.LeboncoinDao
import com.example.data.model.AdvertEntity
import com.example.data.repository.datasource.LeboncoinLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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