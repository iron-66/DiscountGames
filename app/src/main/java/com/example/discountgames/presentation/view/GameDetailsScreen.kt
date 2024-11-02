package com.example.discountgames.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.discountgames.presentation.viewModel.GameViewModel
import coil.compose.rememberImagePainter

@Composable
fun GameDetailsScreen(index: Int, viewModel: GameViewModel) {
    val games = viewModel.gamesList.value
    val game = games.getOrNull(index)

    if (game != null) {
        Column(modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
            Text(text = game.title, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Цена без скидки: ${game.normalPrice}$")
            Text(text = "Текущая цена: ${game.salePrice}$")
            Image(
                painter = rememberAsyncImagePainter(model = game.thumb),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 8.dp),
                contentScale = ContentScale.Crop
            )
        }
    } else {
        Text(text = "Отсутствуют данные по данной игре", modifier = Modifier.padding(16.dp))
    }
}
