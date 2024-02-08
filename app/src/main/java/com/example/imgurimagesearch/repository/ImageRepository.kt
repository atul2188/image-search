package com.example.imgurimagesearch.repository

import com.example.imgurimagesearch.model.network.ApiService
import com.example.imgurimagesearch.model.network.data.ImgurResponse
import com.example.imgurimagesearch.util.Resource
import java.io.IOException
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSearchImages(q: String) : Resource<ImgurResponse> {

        return try {
            val result = apiService.getQueriedTopOfTheWeekImages(q)
            Resource.Success(result)
        }
        catch (e: IOException){
            Resource.Error(message = e.message.toString())
        }
    }
}