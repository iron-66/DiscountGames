package com.example.discountgames.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.discountgames.domain.FavoriteGame

@Database(entities = [FavoriteGame::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteGameDao(): FavoriteGameDao
}