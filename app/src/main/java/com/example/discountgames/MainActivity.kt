package com.example.discountgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.discountgames.data.RetrofitInstance
import com.example.discountgames.data.GameRepository
import com.example.discountgames.domain.GetDiscountedGamesUseCase
import com.example.discountgames.presentation.GameViewModel
import com.example.discountgames.presentation.GameViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitInstance.apiService
        val gameRepository = GameRepository(apiService)
        val getDiscountedGamesUseCase = GetDiscountedGamesUseCase(gameRepository)
        val factory = GameViewModelFactory(getDiscountedGamesUseCase)
        gameViewModel = ViewModelProvider(this, factory)[GameViewModel::class.java]

        setContent {
            MainScreen(viewModel = gameViewModel)
        }
    }
}
