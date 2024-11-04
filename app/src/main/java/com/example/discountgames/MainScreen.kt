package com.example.discountgames

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.discountgames.presentation.view.BottomNavigationBar
import com.example.discountgames.presentation.view.EmptyScreen
import com.example.discountgames.presentation.view.FavoriteScreen
import com.example.discountgames.presentation.view.GameDetailsScreen
import com.example.discountgames.presentation.view.GamesListScreen
import com.example.discountgames.presentation.view.SettingsScreen
import com.example.discountgames.presentation.viewModel.EmptyViewModel
import com.example.discountgames.presentation.viewModel.GameViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: GameViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavHost(navController = navController, startDestination = "gamesList") {
            composable("gamesList") {
                GamesListScreen(navController = navController, viewModel = viewModel)
            }
            composable("gameDetails/{gameId}") { backStackEntry ->
                val gameId  = backStackEntry.arguments?.getString("gameId") ?: ""
                GameDetailsScreen(gameId = gameId, viewModel = viewModel)
            }
            composable("settings") {
                val emptyViewModel: EmptyViewModel = viewModel()
                SettingsScreen(navController = navController, viewModel = viewModel)
            }
            composable("favorite") {
                FavoriteScreen(navController = navController, viewModel = viewModel)
            }
            composable("notifications") {
                val emptyViewModel: EmptyViewModel = viewModel()
                EmptyScreen(navController = navController, viewModel = emptyViewModel)
            }
        }
    }
}
