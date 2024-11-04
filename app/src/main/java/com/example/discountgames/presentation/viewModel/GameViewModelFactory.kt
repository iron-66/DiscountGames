package com.example.discountgames.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.discountgames.data.GamesRepository
import com.example.discountgames.domain.GetDiscountedGamesUseCase
import android.content.Context

class GameViewModelFactory(
    private val getDiscountedGamesUseCase: GetDiscountedGamesUseCase,
    private val gamesRepository: GamesRepository,
    private val context: Context,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(getDiscountedGamesUseCase, gamesRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
