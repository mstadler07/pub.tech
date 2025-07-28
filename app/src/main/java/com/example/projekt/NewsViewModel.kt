package com.example.pubtech_project

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.projekt.storage.FavoritesStorage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsRepository(NewsApiService.create())
    private val _isRefreshing = MutableStateFlow(false)
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    private val context = getApplication<Application>().applicationContext
    private val favoritesStorage = FavoritesStorage(context)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing
    val articles: StateFlow<List<Article>> = _articles
    val favorites: StateFlow<Collection<String>> = favoritesStorage.favoritesFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptySet()
        )

    init {
        viewModelScope.launch {
            try {
                _articles.value = repository.getArticles()
            } catch (e: Exception) {
                Log.d("Loadfail_for_news_api", "Fehler beim Laden der API-Daten")
            }
        }
    }

    fun refreshNews() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                _articles.value = repository.getArticles()
            }catch (e: Exception) {
                Log.e("NewsViewModel", "Fehler beim Aktualisieren", e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            favoritesStorage.toggleFavorite(article.id)
        }
    }

    fun getTagFrequency(): Map<String, Int> {
        return articles.value
            .flatMap { it.tags }
            .groupingBy { it }
            .eachCount()
    }
}