package com.example.data.model.mapper.mapper

import com.example.data.model.AdvertEntity
import com.example.domain.model.Advert

object AdvertMapper {
    fun fromEntity(advertEntity: AdvertEntity): Advert {
        return Advert(
            advertEntity.albumId,
            advertEntity.id,
            advertEntity.thumbnailUrl,
            advertEntity.title,
            advertEntity.url
        )
    }
}