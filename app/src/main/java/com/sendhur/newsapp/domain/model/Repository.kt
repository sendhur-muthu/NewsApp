package com.sendhur.newsapp.domain.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sendhur.newsapp.data.remote.Constants
import com.sendhur.newsapp.data.remote.NewsApi
import com.sendhur.newsapp.data.remote.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsApi: NewsApi
) {
    fun searchNews(query: String): Flow<PagingData<SearchResponse.Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.pageSize),
            pagingSourceFactory = {
                SearchPagingSource(newsApi, query)
            }
        ).flow
    }
}