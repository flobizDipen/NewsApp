package com.dipen.newsapp.data.datasource

import com.dipen.newsapp.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    suspend fun getNewsHeadline(country: String): Response<NewsResponse>
}