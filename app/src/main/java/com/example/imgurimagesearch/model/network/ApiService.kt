package com.example.imgurimagesearch.model.network

import com.example.imgurimagesearch.model.network.data.ImgurResponse
import com.example.imgurimagesearch.util.Constants.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization:CLIENT-ID ${CLIENT_ID}")
    @GET("gallery/search/top/week")
    suspend fun getQueriedTopOfTheWeekImages(
        @Query("q")
        query: String
    ): ImgurResponse
}