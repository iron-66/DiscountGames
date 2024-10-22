package com.example.discountgames.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.discountgames.viewModel.GameViewModel

@Composable
fun GamesListScreen(navController: NavHostController, viewModel: GameViewModel) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 35.dp)
    ) {
        items(viewModel.gamesList.size) { index ->
            val game = viewModel.gamesList[index]
            Text(
                text = game.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable {
                        navController.navigate("gameDetails/$index")
                    }
            )
        }
    }
}
