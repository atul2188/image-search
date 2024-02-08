package com.example.imgurimagesearch.presentation

import com.example.imgurimagesearch.domain.model.remote.Data

class HomeUiState(
    val isLoading: Boolean = false,
    val data: List<Data> = emptyList(),
    val error: String = ""
) {
}