package com.example.imgurimagesearch.ui

sealed class HomeEvent {
    data class getImages(val query: String) : HomeEvent()
}