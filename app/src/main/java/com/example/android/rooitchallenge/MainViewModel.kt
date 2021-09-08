package com.example.android.rooitchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.rooitchallenge.data.NewsApi
import com.example.android.rooitchallenge.data.NewsRepository
import com.example.android.rooitchallenge.data.NewsRepositoryImpl
import com.example.android.rooitchallenge.data.domain.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    init {
        refreshNews()
    }

    fun refreshNews() = viewModelScope.launch {
        repository.updateNews()
        getNews()
    }

    private fun getNews() = viewModelScope.launch {
        repository.getNews()
            .collect {
                _newsList.value = it
            }
    }
}