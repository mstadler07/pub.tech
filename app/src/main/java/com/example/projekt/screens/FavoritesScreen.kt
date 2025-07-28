package com.example.projekt.widgets

import NewsItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pubtech_project.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(viewModel: NewsViewModel = viewModel()) {
    val articles by viewModel.articles.collectAsState()
    val favorites by viewModel.favorites.collectAsState()


    val favoriteArticles = articles.filter { it.id in favorites }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BR24 News") })}
    ){ padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp), // Abstand zwischen Items
        )  {
            items(favoriteArticles) { article ->
                NewsItem(article = article, isFavorite = true, onToggleFavorite = {
                    viewModel.toggleFavorite(article)
                })
            }
        }

    }
}