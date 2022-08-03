package com.example.domain.useCase

import com.example.domain.repository.AdvertRepository
import javax.inject.Inject

class GetAdvertUseCase  @Inject constructor(private val advertRepository: AdvertRepository) {
    suspend operator fun invoke() = advertRepository.getAdverts()
}