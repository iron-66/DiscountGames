package com.example.discountgames.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountgames.domain.Game
import com.example.discountgames.domain.GetDiscountedGamesUseCase
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel(
    private val getDiscountedGamesUseCase: GetDiscountedGamesUseCase
) : ViewModel() {

    private val _gamesList = mutableStateOf<List<Game>>(emptyList())
    val gamesList: State<List<Game>> = _gamesList

    init {
        loadDiscountedGames()
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
}