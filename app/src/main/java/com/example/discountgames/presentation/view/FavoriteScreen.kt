package com.example.discountgames.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.discountgames.presentation.viewModel.GameViewModel

@Composable
fun FavoriteScreen(navController: NavHostController, viewModel: GameViewModel) {
    val favoriteGames = viewModel.favoriteGames.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(favoriteGames.size) { index ->
            val game = favoriteGames[index]
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = game.title)
                Text(text = "Цена без скидки: ${game.normalPrice}$")
                Text(text = "Текущая цена: ${game.salePrice}$")
            }
        }
    }
}