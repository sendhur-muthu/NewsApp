package com.sendhur.newsapp.data.remote

import com.sendhur.newsapp.domain.model.SearchResponse
import com.sendhur.newsapp.domain.model.TopHeadlines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = Constants.country,
        @Query("apiKey") apiKey: String = Constants.apiKey
    ): Response<TopHeadlines>

    @GET("v2/everything")
    suspend fun getSearchResults(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = Constants.apiKey,
        @Query("pageSize") pageSize: Int = Constants.pageSize,
        @Query("page") page: Int
    ): SearchResponse
}