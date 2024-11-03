package com.example.discountgames.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.discountgames.presentation.viewModel.GameViewModel
import com.example.discountgames.domain.FavoriteGame

@Composable
fun GameDetailsScreen(gameId: String, viewModel: GameViewModel) {
    val game = viewModel.gamesList.value.find { it.gameID  == gameId }
    val isFavorite = viewModel.isGameInFavorites(gameId)

    if (game != null) {
        Column(modifier = Modifier.padding(top = 50.dp, start = 15.dp, end = 15.dp)) {
            Text(text = game.title, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Цена без скидки: ${game.normalPrice}$")
            Text(text = "Текущая цена: ${game.salePrice}$")
            Image(
                painter = rememberAsyncImagePainter(model = game.thumb),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 10.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = {
                if (isFavorite) {
                    viewModel.removeGameFromFavorites(game.gameID)
                } else {
                    viewModel.addGameToFavorites(
                        FavoriteGame(
                            id = game.gameID ,
                            title = game.title,
                            normalPrice = game.normalPrice,
                            salePrice = game.salePrice,
                            thumb = game.thumb
                        )
                    )
                }
            }) {
                Text(if (isFavorite) "Удалить из избранного" else "Добавить в избранное")
            }
        }
    } else {
        Column(modifier = Modifier.padding(top = 50.dp, start = 15.dp, end = 15.dp)) {
            Text(text = "Отсутствуют данные по данной игре")
            Spacer(modifier = Modifier.height(16.dp))
            if (isFavorite) {
                Button(onClick = {
                    viewModel.removeGameFromFavorites(gameId)
                }) {
                    Text("Удалить из избранного")
                }
            }
        }
    }
}
