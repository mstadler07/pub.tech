package com.example.pubtech_project

import android.util.Log
import coil.util.Logger
import com.example.pubtech_project.NewsResponse
import com.example.pubtech_project.NewsApiService

class NewsRepository(private val api: NewsApiService) {
    suspend fun getArticles(): List<Article> {
        try {
            val response = api.getNews()
            val result = response.data.sortedByDescending { it.publicationDate }
            return result
        } catch (e: Exception) {
            Log.e("API_ERROR", "Fehler beim Aufruf: ", e)
            return emptyList()
        }
    }
}