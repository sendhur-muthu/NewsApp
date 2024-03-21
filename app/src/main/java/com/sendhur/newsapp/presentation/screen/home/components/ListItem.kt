package com.sendhur.newsapp.presentation.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sendhur.newsapp.R
import com.sendhur.newsapp.domain.model.SearchResponse

@ExperimentalCoilApi
@Composable
fun ListContent(modifier: Modifier, articles: LazyPagingItems<SearchResponse.Article>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(articles.itemCount) {
            articles[it]?.let { news ->
                NewsItem(news)
            }
        }
    }
}

/*
* items(
            items = news,
            key = { unsplashImage ->
                unsplashImage.id
            }
        ) { unsplashImage ->
            unsplashImage?.let { UnsplashItem(unsplashImage = it) }
        }*/

@ExperimentalCoilApi
@Composable
fun NewsItem(news: SearchResponse.Article) {
    val painter = rememberImagePainter(data = news.urlToImage) {
        crossfade(durationMillis = 1000)
        error(R.drawable.ic_placeholder)
        placeholder(R.drawable.ic_placeholder)
    }
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            painter = painter,
            contentDescription = "News Image",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = news.title,
                color = MaterialTheme.typography.bodyLarge.color,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Text(text = news.content,
                color = MaterialTheme.typography.bodyMedium.color,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }
    }
}