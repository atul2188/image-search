package com.example.imgurimagesearch.domain.model.remote

import com.google.gson.annotations.SerializedName

data class ImgurResponse(
    @SerializedName("data")
    val imageData: Data,
    val status: Int,
    val success: Boolean
)