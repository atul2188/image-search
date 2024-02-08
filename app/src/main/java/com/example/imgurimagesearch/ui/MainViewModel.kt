package com.example.imgurimagesearch.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imgurimagesearch.repository.ImageRepository
import com.example.imgurimagesearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    val state: MutableState<HomeUiState> = mutableStateOf(HomeUiState())

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.getImages -> getImageList(event.query)
        }
    }

    fun getImageList(query: String) = viewModelScope.launch {
        val result = imageRepository.getSearchImages(query)

        when(result){
            is Resource.Loading -> {
                state.value = HomeUiState(isLoading = true)
            }
            is Resource.Error -> {
                state.value = HomeUiState(error = "Something Went Wrong..!!")
            }
            is Resource.Success -> {
                result.data?.data?.images?.let {
                    state.value = HomeUiState(data = it)
                }
            }
        }
    }
}