package com.example.imgurimagesearch

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imgurimagesearch.presentation.MainViewModel
import com.example.imgurimagesearch.presentation.screens.HomeScreen
import com.example.imgurimagesearch.ui.theme.ImgurImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImgurImageSearchTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                MyApp {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                    Text("Imgur Image Search")
                                }
                            )
                        }
                    ) {
                        HomeScreen(
                            viewModel.state.value,
                            viewModel::onEvent,
                            modifier = Modifier.padding(it)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable ()->Unit) {
   content()
}
