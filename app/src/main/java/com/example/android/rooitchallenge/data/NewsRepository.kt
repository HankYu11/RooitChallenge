package com.example.android.rooitchallenge.data

import com.example.android.rooitchallenge.data.domain.News
import com.example.android.rooitchallenge.data.local.RealmNews
import com.example.android.rooitchallenge.data.local.asDomain
import com.example.android.rooitchallenge.data.remote.asRealmNews
import io.realm.Realm
import io.realm.com_example_android_rooitchallenge_data_local_RealmNewsRealmProxy.insert
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

interface NewsRepository {
    suspend fun updateNews()
    fun getNews(): Flow<List<News>>
}

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val dispatcher: CoroutineDispatcher
) : NewsRepository {
    private lateinit var realm: Realm

    override suspend fun updateNews() {
        withContext(dispatcher) {
            if (::realm.isInitialized.not()) {
                realm = Realm.getDefaultInstance()
            }
            val articles = newsApi.getNews().articles.asRealmNews()
            realm.executeTransaction {
                it.insertOrUpdate(articles)
            }
        }
    }

    override fun getNews(): Flow<List<News>> = flow {
        if (::realm.isInitialized.not()) {
            realm = Realm.getDefaultInstance()
        }
        val newsList = realm.where(RealmNews::class.java).findAll()
            .toList()
            .asDomain()
        emit(newsList)
    }.flowOn(dispatcher)
}