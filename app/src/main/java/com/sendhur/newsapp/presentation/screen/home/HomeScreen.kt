package com.sendhur.newsapp.presentation.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.sendhur.newsapp.presentation.screen.home.components.ListContent
import com.sendhur.newsapp.presentation.screen.home.components.SearchWidget

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery
    val searchedNews = viewModel.searchedNews.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    viewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    viewModel.searchNews(query = it)
                },
                onCloseClicked = {

                }
            )
        },
        content = {
            ListContent(modifier = Modifier.padding(it), articles = searchedNews)
        }
    )
}