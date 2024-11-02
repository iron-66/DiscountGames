package com.example.discountgames.domain

import com.example.discountgames.data.GamesRepository

class GetDiscountedGamesUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(storeId: String?, upperPrice: String?): List<Game> {
        return repository.getDiscountedGames(storeId, upperPrice)
    }
}