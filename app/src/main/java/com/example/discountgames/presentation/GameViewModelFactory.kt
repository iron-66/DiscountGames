package com.example.discountgames.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.discountgames.domain.GetDiscountedGamesUseCase

class GameViewModelFactory(
    private val getDiscountedGamesUseCase: GetDiscountedGamesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(getDiscountedGamesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}