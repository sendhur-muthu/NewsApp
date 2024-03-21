package com.sendhur.newsapp.presentation.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sendhur.newsapp.domain.model.Repository
import com.sendhur.newsapp.domain.model.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedNews = MutableStateFlow<PagingData<SearchResponse.Article>>(PagingData.empty())
    val searchedNews = _searchedNews

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            repository.searchNews(query = query).cachedIn(viewModelScope).collect {
                _searchedNews.value = it
            }
        }
    }
}