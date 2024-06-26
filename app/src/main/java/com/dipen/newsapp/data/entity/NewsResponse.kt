package com.dipen.newsapp.data.entity

data class NewsResponse(
    val status: String?,
    val totalResult: Int?,
    val articles: List<Article>
)

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val content: String?,
    val publishedAt: String?,
)

data class Source(
    val id: String?,
    val name: String?,
)
