package com.oneearth.shetkari.Models

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)


