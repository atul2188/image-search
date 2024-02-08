package com.example.imgurimagesearch.domain.usecases

import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.domain.repository.ImageRepository
import com.example.imgurimagesearch.util.Resource

class SearchImageUseCase(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(query: String) : Resource<List<Data>>{
        return imageRepository.getSearchImages(query)
    }
}