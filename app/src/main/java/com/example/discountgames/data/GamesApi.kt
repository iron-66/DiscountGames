package com.example.discountgames.data

import com.example.discountgames.domain.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("deals")
    suspend fun getDiscountedGames(
        @Query("storeID") storeId: String?,
        @Query("upperPrice") upperPrice: String?
    ): List<Game>
}
