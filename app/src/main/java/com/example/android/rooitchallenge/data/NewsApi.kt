package com.example.android.rooitchallenge.data

import com.example.android.rooitchallenge.data.remote.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "043fe0e0d24e42eb82dfb12d666ae241"
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}