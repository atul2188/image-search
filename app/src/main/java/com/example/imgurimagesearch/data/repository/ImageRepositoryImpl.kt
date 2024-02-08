package com.example.imgurimagesearch.data.repository

import com.example.imgurimagesearch.data.ApiService
import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.domain.repository.ImageRepository
import com.example.imgurimagesearch.util.Resource
import java.io.IOException

class ImageRepositoryImpl(
    private val apiService: ApiService
) : ImageRepository {
    override suspend fun getSearchImages(q: String) : Resource<List<Data>> {

        return try {
            val result = apiService.getQueriedTopOfTheWeekImages(q)
            Resource.Success(result.data)
        }
        catch (e: IOException){
            Resource.Error(message = e.message.toString())
        }
    }
}