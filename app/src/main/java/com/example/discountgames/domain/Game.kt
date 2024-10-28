package com.example.discountgames.domain

data class Game(
    val title: String,
    val normalPrice: String,
    val salePrice: String,
    val dealId: String
)