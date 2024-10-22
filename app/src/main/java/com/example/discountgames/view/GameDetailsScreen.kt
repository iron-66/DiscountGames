package com.example.discountgames.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.discountgames.viewModel.GameViewModel

@Composable
fun GameDetailsScreen(index: Int, viewModel: GameViewModel) {
    val game = viewModel.gamesList[index]

    Column(modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
        Text(text = game.name, modifier = Modifier.padding(bottom = 8.dp))
        Text(text = game.discountDetails)
    }
}
