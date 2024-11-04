package com.example.discountgames.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_games")
data class FavoriteGame(
    @PrimaryKey val id: String,
    val title: String,
    val normalPrice: String,
    val salePrice: String,
    val thumb: String
)
