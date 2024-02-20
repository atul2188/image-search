package com.example.imgurimagesearch.domain.repository

import androidx.paging.PagingData
import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.domain.model.remote.Image
import com.example.imgurimagesearch.util.Resource
import kotlinx.coroutines.flow.Flow


interface ImageRepository{
    suspend fun getSearchImages(q: String) : Flow<PagingData<Image>>
}