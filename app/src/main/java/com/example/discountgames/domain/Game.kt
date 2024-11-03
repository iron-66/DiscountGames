package com.example.discountgames.domain

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("gameID") val gameID: String,
    @SerializedName("title") val title: String,
    @SerializedName("normalPrice") val normalPrice: String,
    @SerializedName("salePrice") val salePrice: String,
    @SerializedName("dealID") val dealId: String,
    @SerializedName("thumb") val thumb: String
)
