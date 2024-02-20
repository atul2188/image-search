package com.example.imgurimagesearch.domain.usecases

import androidx.paging.PagingData
import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.domain.model.remote.Image
import com.example.imgurimagesearch.domain.repository.ImageRepository
import com.example.imgurimagesearch.util.Resource
import kotlinx.coroutines.flow.Flow

class SearchImageUseCase(
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(query: String) : Flow<PagingData<Image>> {
        return imageRepository.getSearchImages(query)
    }
}