package com.example.discountgames.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.discountgames.presentation.viewModel.GameViewModel

@Composable
fun GamesListScreen(navController: NavHostController, viewModel: GameViewModel) {
    val games = viewModel.gamesList.value

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 35.dp)
    ) {
        items(games.size) { index ->
            val game = games[index]
            Text(
                text = game.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable {
                        navController.navigate("gameDetails/${game.gameID}")
                    }
            )
        }
    }
}
