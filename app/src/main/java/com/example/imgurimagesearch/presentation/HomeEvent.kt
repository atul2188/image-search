package com.example.imgurimagesearch.presentation

sealed class HomeEvent {
    data class GetImages(val query: String) : HomeEvent()
    data class UpdateScrollValue(val newValue: Int): HomeEvent()
    data class UpdateMaxScrollingValue(val newValue: Int): HomeEvent()
}