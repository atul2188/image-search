package com.example.imgurimagesearch.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imgurimagesearch.domain.model.remote.Data
import com.example.imgurimagesearch.domain.model.remote.Image
import com.example.imgurimagesearch.domain.repository.ImageRepository
import java.io.IOException

private const val IMGUR_STARTING_PAGE_INDEX = 1
class ImagePagingSource(
    private val query: String,
    private val apiService: ApiService
): PagingSource<Int, Image>() {
    private var totalImages = 0
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val position = params.key ?: IMGUR_STARTING_PAGE_INDEX
        val response = apiService.getQueriedTopOfTheWeekImages(position, query)
        totalImages += response.imageData.images.size

        return try {
            LoadResult.Page(
                data = response.imageData.images,
                prevKey = if (position == IMGUR_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (position == response.imageData.images_count) null else position + 1
                )
        }
        catch (e: IOException){
            LoadResult.Error(e)
        }
    }

}