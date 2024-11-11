package com.example.discountgames.presentation.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountgames.data.GamesRepository
import com.example.discountgames.data.PreferenceKeys
import com.example.discountgames.domain.FavoriteGame
import com.example.discountgames.domain.Game
import com.example.discountgames.domain.GetDiscountedGamesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "filter_preferences")

class GameViewModel(
    private val getDiscountedGamesUseCase: GetDiscountedGamesUseCase,
    private val repository: GamesRepository,
    context: Context
) : ViewModel() {

    private val _gamesList = mutableStateOf<List<Game>>(emptyList())
    val gamesList: State<List<Game>> = _gamesList

    private val _favoriteGames = mutableStateOf<List<FavoriteGame>>(emptyList())
    val favoriteGames: State<List<FavoriteGame>> = _favoriteGames

    private val _selectedRating = mutableStateOf<Int?>(null)
    val selectedRating: State<Int?> = _selectedRating

    private val _selectedYear = mutableStateOf<String?>("Любой")
    val selectedYear: State<String?> = _selectedYear

    private val dataStore = context.dataStore

    val savedRating: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[PreferenceKeys.RATING_KEY] ?: -1
        }

    val savedYear: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferenceKeys.YEAR_KEY] ?: "Любой"
        }

    init {
        loadFilters()
        loadDiscountedGames()
        loadFavoriteGames()
    }

    private fun loadFilters() {
        viewModelScope.launch {
            _selectedRating.value = dataStore.data.map { it[PreferenceKeys.RATING_KEY] }.firstOrNull()
            _selectedYear.value = dataStore.data.map { it[PreferenceKeys.YEAR_KEY] }.firstOrNull()
        }
    }

    private fun loadDiscountedGames() {
        viewModelScope.launch {
            try {
                savedRating.combine(savedYear) { rating, year ->
                    Log.d("GameViewModel", "Loaded Filters -> Rating: $rating, Year: $year")

                    val games = getDiscountedGamesUseCase(
                        storeId = null,
                        upperPrice = null,
                        rating = if (rating != -1) rating else null,
                        yearRange = if (year != "Любой") year else null
                    )

                    games
                }.collect { games ->
                    _gamesList.value = games
                    Log.d("GameViewModel", "Fetched Games List: ${games.size} items")
                }
            } catch (e: IOException) {
                println("Network error: ${e.localizedMessage}")
                _gamesList.value = emptyList()
            } catch (e: Exception) {
                println("An error occurred: ${e.localizedMessage}")
                _gamesList.value = emptyList()
            }
        }
    }

    private fun loadFavoriteGames() {
        viewModelScope.launch {
            repository.getFavoriteGames().collect {
                _favoriteGames.value = it
            }
        }
    }

    fun addGameToFavorites(game: FavoriteGame) {
        viewModelScope.launch {
            repository.addGameToFavorites(
                FavoriteGame(
                    id = game.id,
                    title = game.title,
                    normalPrice = game.normalPrice,
                    salePrice = game.salePrice,
                    thumb = game.thumb
                )
            )
        }
    }

    fun removeGameFromFavorites(gameId: String) {
        viewModelScope.launch {
            repository.removeGameFromFavorites(gameId)
        }
    }

    fun isGameInFavorites(gameId: String): Boolean {
        return favoriteGames.value.any { it.id == gameId }
    }

    fun clearFavorites() {
        viewModelScope.launch {
            repository.clearFavoriteGames()
            loadFavoriteGames()
        }
    }

    fun saveFilters(rating: Int?, year: String?) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferenceKeys.RATING_KEY] = rating ?: -1
                preferences[PreferenceKeys.YEAR_KEY] = year ?: "Любой"
            }
            Log.d("GameViewModel", "Filters saved -> Rating: $rating, Year: $year")
        }
    }

    fun updateRating(rating: Int?) {
        _selectedRating.value = rating
        saveFilters(rating, _selectedYear.value)
    }

    fun updateYear(year: String?) {
        _selectedYear.value = year
        saveFilters(_selectedRating.value, year)
    }
}
