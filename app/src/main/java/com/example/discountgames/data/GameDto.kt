package com.example.discountgames.data;

import com.google.gson.annotations.SerializedName;

data class GameDto(
    @SerializedName("title") val title: String,
    @SerializedName("normalPrice") val normalPrice: String,
    @SerializedName("salePrice") val salePrice: String,
    @SerializedName("dealID") val dealId: String
)
