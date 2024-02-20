package com.example.imgurimagesearch.presentation

import androidx.paging.PagingData
import com.example.imgurimagesearch.domain.model.remote.Image
import kotlinx.coroutines.flow.Flow

data class HomeUiState(
    //val isLoading: Boolean = false,
    //val error: String = "",
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0,
    val images: Flow<PagingData<Image>>? = null
) {
}