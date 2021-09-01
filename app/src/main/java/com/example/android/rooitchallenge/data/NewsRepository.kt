package com.example.android.rooitchallenge.data

import com.example.android.rooitchallenge.data.domain.News
import com.example.android.rooitchallenge.data.remote.Article
import com.example.android.rooitchallenge.data.remote.NewsResponse
import com.example.android.rooitchallenge.data.remote.asDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface NewsRepository {
    fun getNews(): Flow<List<News>>
}

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val dispatcher: CoroutineDispatcher
): NewsRepository {
    override fun getNews(): Flow<List<News>> = flow {
        newsApi.getNews().articles.map {
            it.asDomain()
        }.also {
            emit(it)
        }
    }.flowOn(dispatcher)

}