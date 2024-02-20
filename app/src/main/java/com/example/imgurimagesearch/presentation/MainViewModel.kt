package com.example.imgurimagesearch.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.domain.model.remote.Image
import com.example.imgurimagesearch.domain.repository.ImageRepository
import com.example.imgurimagesearch.domain.usecases.SearchImageUseCase
import com.example.imgurimagesearch.util.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    val state: MutableState<HomeUiState> = mutableStateOf(HomeUiState())

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.GetImages -> getImageList(event.query)
            is HomeEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
            is HomeEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
        }

    }
    private fun updateScrollValue(newValue: Int){
        state.value = state.value.copy(scrollValue = newValue)
    }
    private fun updateMaxScrollingValue(newValue: Int){
        state.value = state.value.copy(maxScrollingValue = newValue)
    }


    fun getImageList(query: String) = viewModelScope.launch {
        val result = imageRepository.getSearchImages(query)
            .cachedIn(viewModelScope)



        state.value = state.value.copy(images = result)
    }



}