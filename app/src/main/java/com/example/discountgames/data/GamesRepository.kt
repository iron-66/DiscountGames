package com.example.discountgames.data

import com.example.discountgames.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class GamesRepository(private val apiService: GamesApi) {

    suspend fun getDiscountedGames(storeId: String?, upperPrice: String?): List<Game> {
        return withContext(Dispatchers.IO) {
            try {
                val gamesList = apiService.getDiscountedGames(storeId, upperPrice)
                gamesList.map { gameInfo ->
                    Game(
                        title = gameInfo.title,
                        normalPrice = gameInfo.normalPrice,
                        salePrice = gameInfo.salePrice,
                        dealId = gameInfo.dealId,
                        thumb = gameInfo.thumb
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