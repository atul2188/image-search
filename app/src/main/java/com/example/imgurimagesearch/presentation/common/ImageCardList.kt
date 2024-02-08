package com.example.imgurimagesearch.presentation.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imgurimagesearch.domain.model.remote.Image
import com.example.imgurimagesearch.util.Constants


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImageCardList(image: Image){
    val showShimmer = remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.link)
                    .build(),
                contentDescription = "This is image from Imgur",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
                    .height(120.dp)
                    .width(120.dp)
            )

            Column {
                if (!image.description.isNullOrEmpty()) {
                    Text(
                        text = "image",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    text = Constants.epochToLocalDateTime(image.datetime.toLong()),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }

    }

}