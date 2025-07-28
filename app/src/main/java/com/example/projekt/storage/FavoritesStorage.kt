package com.example.projekt.storage

import android.content.Context
import androidx.core.content.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

val Context.dataStore by preferencesDataStore(name = "favorites_store")

class FavoritesStorage(context: Context) {
    private val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val _favoritesFlow = MutableStateFlow(loadFavorites())
    val favoritesFlow: StateFlow<List<String>> = _favoritesFlow

    private fun loadFavorites(): List<String> {
        return prefs.getStringSet("favorites", emptySet())!!.toList()
    }

    private fun saveFavorites(favorites: List<String>) {
        prefs.edit {
            putStringSet("favorites", favorites.toSet())
        }
        _favoritesFlow.value = favorites
    }

    fun toggleFavorite(id: String) {
        val current = _favoritesFlow.value.toMutableList()
        if (current.contains(id)) {
            current.remove(id)
        } else {
            current.add(id)
        }
        saveFavorites(current)
    }

}
