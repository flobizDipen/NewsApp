package com.dipen.newsapp.data.api

import com.dipen.newsapp.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "ced57c9456814511bd34bf2051f3dcfd",
    ): Response<NewsResponse>
}