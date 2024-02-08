package com.example.imgurimagesearch.model.network.data

data class Image(
    val animated: Boolean,
    val bandwidth: Long,
    val datetime: Int,
    val description: Any,
    val height: Int,
    val id: String,
    val link: String,
    val size: Int,
    val title: Any,
    val type: String,
    val views: Int,
    val width: Int
)