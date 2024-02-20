package com.example.imgurimagesearch.di

import com.example.imgurimagesearch.data.ApiService
import com.example.imgurimagesearch.data.ImagePagingSource
import com.example.imgurimagesearch.data.repository.ImageRepositoryImpl
import com.example.imgurimagesearch.domain.repository.ImageRepository
import com.example.imgurimagesearch.domain.usecases.SearchImageUseCase
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

    @Singleton
    @Provides
    fun provideImageRepository(apiService: ApiService) : ImageRepository {
        return ImageRepositoryImpl(apiService = apiService)
    }

    @Singleton
    @Provides
    fun provideSearchImageUseCase(imageRepository: ImageRepository) : SearchImageUseCase{
        return SearchImageUseCase(imageRepository)
    }
}