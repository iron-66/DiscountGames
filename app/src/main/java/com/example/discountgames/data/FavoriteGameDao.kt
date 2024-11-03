package com.example.discountgames.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.discountgames.domain.FavoriteGame
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGameDao {
    @Query("SELECT * FROM favorite_games")
    fun getFavoriteGames(): Flow<List<FavoriteGame>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: FavoriteGame)

    @Query("DELETE FROM favorite_games WHERE id = :gameId")
    suspend fun deleteGame(gameId: String)

    @Query("DELETE FROM favorite_games")
    suspend fun clearFavorites()
}