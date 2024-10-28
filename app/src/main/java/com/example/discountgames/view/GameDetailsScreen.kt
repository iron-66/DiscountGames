package com.example.discountgames.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.discountgames.presentation.GameViewModel

@Composable
fun GameDetailsScreen(index: Int, viewModel: GameViewModel) {
    val games = viewModel.gamesList.value
    val game = games.getOrNull(index)

    if (game != null) {
        Column(modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
            Text(text = game.title, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Цена без скидки: ${game.normalPrice}$")
            Text(text = "Текущая цена: ${game.salePrice}$")
        }
    } else {
        Text(text = "У данной игры отсутствует описание и цена", modifier = Modifier.padding(16.dp))
    }
}
