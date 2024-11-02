package com.example.discountgames.data

import com.example.discountgames.domain.FavoriteGame
import com.example.discountgames.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.IOException

class GamesRepository(private val apiService: GamesApi, private val favoriteGameDao: FavoriteGameDao) {
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

    fun getFavoriteGames(): Flow<List<FavoriteGame>> = favoriteGameDao.getFavoriteGames()

    suspend fun addGameToFavorites(game: FavoriteGame) {
        favoriteGameDao.insertGame(game)
    }

    suspend fun removeGameFromFavorites(gameId: String) {
        favoriteGameDao.deleteGame(gameId)
    }
}