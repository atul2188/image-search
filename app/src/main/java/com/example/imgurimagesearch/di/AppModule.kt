package com.example.imgurimagesearch.di

import com.example.imgurimagesearch.model.network.ApiService
import com.example.imgurimagesearch.repository.ImageRepository
import com.example.imgurimagesearch.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideImageRepository(apiService: ApiService) : ImageRepository {
        return ImageRepository(apiService = apiService)
    }
}