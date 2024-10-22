package com.example.discountgames.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.discountgames.viewModel.EmptyViewModel

@Composable
fun EmptyScreen(navController: NavHostController, viewModel: EmptyViewModel) {
    val text = viewModel.text

    Column(modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
        Text(text = text, modifier = Modifier.padding(bottom = 8.dp))
    }
}
