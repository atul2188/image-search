package com.example.imgurimagesearch.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imgurimagesearch.presentation.Dimens.MediumPadding1
import com.example.imgurimagesearch.presentation.HomeEvent
import com.example.imgurimagesearch.presentation.HomeUiState
import com.example.imgurimagesearch.presentation.common.ImageCardGrid
import com.example.imgurimagesearch.presentation.common.ImageCardList


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    state: HomeUiState = HomeUiState(isLoading = true),
    event: (HomeEvent) -> Unit,
    modifier: Modifier
    ) {

    val query: MutableState<String> = remember {
        mutableStateOf("")
    }

    val checked : MutableState<Boolean> =  remember { mutableStateOf(true) }

    Surface(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(
                    top = MediumPadding1,
                    start = MediumPadding1,
                    end = MediumPadding1
                )
                .statusBarsPadding()
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                    event(HomeEvent.getImages(query.value))
                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row( verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Select Display", fontSize = 20.sp)
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "List", fontSize = 20.sp)
                Switch(
                    modifier = Modifier.width(80.dp),
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue,
                        uncheckedTrackColor = Color.Green
                    )
                )
                Text(text = "Grid", fontSize = 20.sp)
            }

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            if (state.error.isNotBlank()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(modifier = Modifier.align(Alignment.Center), text = state.error)
                }
            }

            if (state.data.isNotEmpty()) {
                if (checked.value) {
                    ImagesGridDisplay(state = state)
                }
                else {
                    ImageListDisplay(state = state)
                }
            }

        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImageListDisplay(state: HomeUiState){
    LazyColumn(modifier = Modifier.fillMaxSize()
        .background(color = MaterialTheme.colorScheme.secondary),
        contentPadding = PaddingValues(16.dp)
    ){
        state.data.forEach { imageData ->
            imageData?.images?.let { imageList ->
                items(imageList){
                    ImageCardList(image = it)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImagesGridDisplay(state: HomeUiState){
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
        //Log.d("HomeScreen", "Data Size is" + state.data.size.toString())
        state.data.forEach { imageData ->
            imageData?.images?.let { it ->
                items(it){
                    //Log.d("HomeScreen", "URL: " + it.link + " Title: " + it.title)
                    ImageCardGrid(image = it)
                }
            }
        }

    }
}



