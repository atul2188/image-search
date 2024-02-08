package com.example.imgurimagesearch.model.network.data

data class Data(
    val account_id: Int,
    val account_url: String,
    val comment_count: Int,
    val cover: String,
    val datetime: Int,
    val description: Any,
    val downs: Int,
    val id: String,
    val images: List<Image>,
    val images_count: Int,
    val is_album: Boolean,
    val layout: String,
    val link: String,
    val points: Int,
    val privacy: String,
    val score: Int,
    val title: String,
    val ups: Int,
    val views: Int,
    val vote: Any
)