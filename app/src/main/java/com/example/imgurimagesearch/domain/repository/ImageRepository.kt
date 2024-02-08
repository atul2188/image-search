package com.example.imgurimagesearch.domain.repository

import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.util.Resource


interface ImageRepository{
    suspend fun getSearchImages(q: String) : Resource<List<Data>>
}