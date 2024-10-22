package com.example.discountgames.model

data class Game(
    val id: String,
    val title: String,
    val salePrice: String,
    val normalPrice: String,
    val savings: String,
    val thumb: String
)