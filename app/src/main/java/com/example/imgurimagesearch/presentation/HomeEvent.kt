package com.example.imgurimagesearch.presentation

sealed class HomeEvent {
    data class getImages(val query: String) : HomeEvent()
}