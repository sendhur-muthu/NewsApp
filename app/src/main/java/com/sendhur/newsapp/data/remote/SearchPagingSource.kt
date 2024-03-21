package com.sendhur.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sendhur.newsapp.domain.model.SearchResponse


class SearchPagingSource(
    private val newsApi: NewsApi,
    private val query: String
): PagingSource<Int, SearchResponse.Article>() {
    override fun getRefreshKey(state: PagingState<Int, SearchResponse.Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResponse.Article> {
        val currentPage = params.key ?: 1
        return try {
            val response = newsApi.getSearchResults(query = query, page = currentPage)
            val endOfPaginationReached = response.articles.isEmpty()
            if (response.articles.isNotEmpty()) {
                LoadResult.Page(
                    data = response.articles,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}