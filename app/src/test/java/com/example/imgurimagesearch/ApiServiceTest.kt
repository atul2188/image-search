package com.example.imgurimagesearch

import com.example.imgurimagesearch.data.ApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: ApiService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Test
    fun testGetQueriedTopOfTheWeekImages() = runTest{

        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getQueriedTopOfTheWeekImages(pageId = 1, query = "Cats")
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.success)

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}