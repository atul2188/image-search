package com.example.imgurimagesearch.ui

import com.example.imgurimagesearch.model.network.data.Image

class HomeUiState(
    val isLoading: Boolean = false,
    val data: List<Image> = emptyList(),
    val error: String = ""
) {
}