package com.dipen.newsapp.ui.repository

import com.dipen.newsapp.data.datasource.NewsDataSource
import com.dipen.newsapp.data.entity.NewsResponse
import com.dipen.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    //suspend fun getHeadline(country: String) = newsDataSource.getNewsHeadline(country)

    suspend fun getHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadline(country)
            if (response.isSuccessful && response.body() != null)
                emit(ResourceState.Success(response.body()!!))
            else
                emit(ResourceState.Error("Error Fetching news data"))

        }.catch {
            emit(ResourceState.Error(it.localizedMessage ?: "Some Error in flow"))
        }
    }
}