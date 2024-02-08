package com.example.imgurimagesearch.data

import com.example.imgurimagesearch.domain.model.remote.ImgurResponse
import com.example.imgurimagesearch.util.Constants.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization:CLIENT-ID $CLIENT_ID")
    @GET("gallery/search/top/week")
    suspend fun getQueriedTopOfTheWeekImages(
        @Query("q")
        query: String,

        @Query("q_type")
        queryType: String = "jpg",

        @Query("q_size_px")
        querySize: String = "small"
    ): ImgurResponse
}