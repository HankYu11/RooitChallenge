package com.example.android.rooitchallenge.di

import com.example.android.rooitchallenge.data.NewsApi
import com.example.android.rooitchallenge.data.NewsRepository
import com.example.android.rooitchallenge.data.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(newsApi: NewsApi): NewsRepository =
        NewsRepositoryImpl(newsApi, Executors.newSingleThreadExecutor().asCoroutineDispatcher())

}