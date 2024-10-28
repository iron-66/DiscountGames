package com.example.discountgames.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("deals")
    suspend fun getDiscountedGames(
        @Query("storeID") storeId: String?,
        @Query("upperPrice") upperPrice: String?
    ): List<GameDto>
}
