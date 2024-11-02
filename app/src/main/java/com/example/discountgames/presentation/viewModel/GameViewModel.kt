package com.example.discountgames.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountgames.data.GamesRepository
import com.example.discountgames.domain.FavoriteGame
import com.example.discountgames.domain.Game
import com.example.discountgames.domain.GetDiscountedGamesUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel(
    private val getDiscountedGamesUseCase: GetDiscountedGamesUseCase,
    private val repository: GamesRepository
) : ViewModel() {

    private val _gamesList = mutableStateOf<List<Game>>(emptyList())
    val gamesList: State<List<Game>> = _gamesList

    private val _favoriteGames = mutableStateOf<List<FavoriteGame>>(emptyList())
    val favoriteGames: State<List<FavoriteGame>> = _favoriteGames

    init {
        loadDiscountedGames()
        loadFavoriteGames()
    }

    private fun loadDiscountedGames() {
        viewModelScope.launch {
            try {
                val games = getDiscountedGamesUseCase(storeId = null, upperPrice = null)
                _gamesList.value = games
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
}