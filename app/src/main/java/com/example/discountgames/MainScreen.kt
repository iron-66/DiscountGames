package com.example.discountgames

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.discountgames.view.EmptyScreen
import com.example.discountgames.view.GameDetailsScreen
import com.example.discountgames.view.GamesListScreen
import com.example.discountgames.viewModel.EmptyViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: com.example.discountgames.presentation.GameViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, selectedRoute = "gamesList")
        }
    ) {
        NavHost(navController = navController, startDestination = "gamesList") {
            composable("gamesList") {
                GamesListScreen(navController = navController, viewModel = viewModel)
            }
            composable("gameDetails/{index}") { backStackEntry ->
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                GameDetailsScreen(index = index, viewModel = viewModel)
            }
            composable("newGames") {
                val emptyViewModel: EmptyViewModel = viewModel()
                EmptyScreen(navController = navController, viewModel = emptyViewModel)
            }
            composable("freeGames") {
                val emptyViewModel: EmptyViewModel = viewModel()
                EmptyScreen(navController = navController, viewModel = emptyViewModel)
            }
            composable("notifications") {
                val emptyViewModel: EmptyViewModel = viewModel()
                EmptyScreen(navController = navController, viewModel = emptyViewModel)
            }
        }
    }
}
