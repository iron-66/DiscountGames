package com.example.discountgames.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Game(val name: String, val discountDetails: String)

class GameViewModel : ViewModel() {
    private val games = mutableStateListOf(
        Game("Cyberpunk 2077", "50% off, $29.99"),
        Game("Red Dead Redemption 2", "40% off, $35.99"),
        Game("The Witcher 3", "70% off, $14.99"),
        Game("Assassin's Creed Valhalla", "60% off, $23.99"),
        Game("Hades", "30% off, $17.49")
    )

    val gamesList: SnapshotStateList<Game> get() = games
}
