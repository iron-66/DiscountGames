package com.example.discountgames.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.discountgames.presentation.viewModel.GameViewModel

@Composable
fun FavoriteScreen(navController: NavHostController, viewModel: GameViewModel) {
    val favoriteGames = viewModel.favoriteGames.value

    if (favoriteGames.isEmpty()) {
        Text(
            text = "Вы пока не добавили ничего в избранное",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 15.dp, end = 15.dp)
        )
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
        ) {
            items(favoriteGames.size) { index ->
                val game = favoriteGames[index]
                Column(modifier = Modifier
                    .padding(top = 30.dp, start = 15.dp, end = 15.dp)
                    .clickable {
                        navController.navigate("gameDetails/${game.id}")
                    }
                ){
                    Text(text = game.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                    )
                    Text(text = "Цена без скидки: ${game.normalPrice}$")
                    Text(text = "Текущая цена: ${game.salePrice}$")
                }
            }
        }
    }
}
