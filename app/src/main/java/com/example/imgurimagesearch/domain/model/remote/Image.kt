package com.example.imgurimagesearch.domain.model.remote

data class Image(
    val animated: Boolean,
    val bandwidth: Long,
    val datetime: Int,
    val description: String,
    val height: Int,
    val id: String,
    val link: String,
    val size: Int,
    val title: String,
    val type: String,
    val views: Int,
    val width: Int
)