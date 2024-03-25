package com.dipen.newsapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dipen.newsapp.ui.components.EmptyState
import com.dipen.newsapp.ui.components.Loader
import com.dipen.newsapp.ui.components.NewsRowComponent
import com.dipen.newsapp.ui.viewModel.NewsViewModel
import com.dipen.utilities.ResourceState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {

    val newsResponse by viewModel.news.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F
    ) {
        100
    }

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) { page: Int ->
        when (newsResponse) {
            is ResourceState.Loading -> {
                Loader()
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success).data
                if (response.articles.isNotEmpty())
                    NewsRowComponent(page, response.articles[page])
                else
                    EmptyState()
            }

            is ResourceState.Error -> {
                val error = (newsResponse as ResourceState.Error).error
            }
        }
    }


}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

