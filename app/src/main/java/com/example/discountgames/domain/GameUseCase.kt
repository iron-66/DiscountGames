package com.example.discountgames.domain

import com.example.discountgames.data.GameRepository
import com.example.discountgames.domain.Game

class GetDiscountedGamesUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(storeId: String?, upperPrice: String?): List<Game> {
        return repository.getDiscountedGames(storeId, upperPrice)
    }
}