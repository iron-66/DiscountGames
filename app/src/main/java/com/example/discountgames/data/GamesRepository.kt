package com.example.discountgames.data

import com.example.discountgames.domain.FavoriteGame
import com.example.discountgames.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GamesRepository(private val apiService: GamesApi, private val favoriteGameDao: FavoriteGameDao) {
    suspend fun getDiscountedGames(storeId: String?, upperPrice: String?, rating: Int?, yearRange: String?): List<Game> {
        return withContext(Dispatchers.IO) {
            try {
                val gamesList = apiService.getDiscountedGames(storeId, upperPrice)
                val sdf = SimpleDateFormat("yyyy", Locale.getDefault())

                val filteredGames = gamesList.filter { game ->
                    val isRatingMatch = rating == null || game.steamRatingPercent / 20 == rating

                    val releaseDateUnix = game.releaseDate.toLongOrNull()
                    val date = if (releaseDateUnix != null) Date(releaseDateUnix * 1000L) else null
                    val releaseYear = date?.let { sdf.format(it).toInt() }

                    val isYearMatch = if (yearRange == null) {
                        true
                    } else {
                        val (startYear, endYear) = yearRange.split("-").map { it.toInt() }
                        releaseYear != null && releaseYear in startYear..endYear
                    }

                    isRatingMatch && isYearMatch
                }

                filteredGames.map { gameInfo ->
                    Game(
                        gameID = gameInfo.gameID,
                        title = gameInfo.title,
                        normalPrice = gameInfo.normalPrice,
                        salePrice = gameInfo.salePrice,
                        dealId = gameInfo.dealId,
                        thumb = gameInfo.thumb,
                        steamRatingPercent = gameInfo.steamRatingPercent,
                        releaseDate = gameInfo.releaseDate
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

    suspend fun clearFavoriteGames() {
        favoriteGameDao.clearFavorites()
    }
}
