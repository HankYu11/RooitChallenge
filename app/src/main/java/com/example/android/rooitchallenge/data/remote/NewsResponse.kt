package com.example.android.rooitchallenge.data.remote

import com.example.android.rooitchallenge.data.domain.News
import com.example.android.rooitchallenge.data.local.RealmNews
import com.squareup.moshi.Json

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val title: String,
    val urlToImage: String?,

)

data class Source(
    val name: String
)

fun List<Article>.asRealmNews(): List<RealmNews> {
    return map{
        val realmNews = RealmNews()
        realmNews.imageUrl = it.urlToImage
        realmNews.titleSource = "${it.title} - ${it.source}"
        realmNews
    }
}
