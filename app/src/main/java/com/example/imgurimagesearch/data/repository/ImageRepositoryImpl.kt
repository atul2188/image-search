package com.example.imgurimagesearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imgurimagesearch.data.ApiService
import com.example.imgurimagesearch.data.ImagePagingSource
import com.example.imgurimagesearch.domain.model.remote.Image
import com.example.imgurimagesearch.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(
    private val apiService: ApiService
) : ImageRepository {
    override suspend fun getSearchImages(q: String) : Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(q, apiService)}
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}