package com.example.imgurimagesearch.domain.model.remote

data class ImgurResponse(
    val data: List<Data>,
    val status: Int,
    val success: Boolean
)