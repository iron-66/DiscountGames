package com.example.discountgames.data

import com.example.discountgames.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class GameRepository(private val apiService: ApiService) {

    suspend fun getDiscountedGames(storeId: String?, upperPrice: String?): List<Game> {
        return withContext(Dispatchers.IO) {
            try {
                val gameDtos = apiService.getDiscountedGames(storeId, upperPrice)
                gameDtos.map { dto ->
                    Game(
                        title = dto.title,
                        normalPrice = dto.normalPrice,
                        salePrice = dto.salePrice,
                        dealId = dto.dealId
                    )
                }
            } catch (e: IOException) {
                println("Network error: ${e.localizedMessage}")
                emptyList()
            } catch (e: Exception) {
                println("An error occurred: ${e.localizedMessage}")
                emptyList()
            }
        }
    }
}