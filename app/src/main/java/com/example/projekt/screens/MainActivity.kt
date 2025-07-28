package com.example.projekt.screens

import NewsList
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekt.ui.theme.ProjektTheme
import com.example.projekt.widgets.FavoritesScreen
import com.example.pubtech_project.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjektTheme {
                NewsApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NewsApp() {
    val context = LocalContext.current.applicationContext as Application

    val newsViewModel: NewsViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return NewsViewModel(context) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )

    var selectedTab by remember { mutableStateOf("news") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BR24 News") },
                actions = {
                    Text(
                        text = if (selectedTab == "news" || selectedTab == "tags") "Favoriten" else  "News",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                selectedTab = if (selectedTab == "news" || selectedTab == "tags") "favorites" else "news"
                            }
                    )
                    Text(
                        text =  if (selectedTab == "news" || selectedTab == "favorites") "Tags" else "News",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                selectedTab = if (selectedTab == "news" || selectedTab == "favorites") "tags" else "news"
                            }
                    )
                }
            )
        }
    ) { padding ->
        when (selectedTab) {
            "news" -> NewsList(newsViewModel, Modifier.padding(padding))
            "favorites" -> FavoritesScreen(newsViewModel)
            "tags" -> TagCloudScreen(newsViewModel)
        }
    }
}

