package com.example.discountgames.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.discountgames.presentation.viewModel.GameViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: GameViewModel) {
    val selectedRating by viewModel.selectedRating
    val selectedYear by viewModel.selectedYear

    Column(modifier = Modifier.padding(top = 50.dp, start = 15.dp, end = 15.dp)) {
        Text(text = "Выберите рейтинг:")
        RatingDropdown(selectedRating) { newRating ->
            viewModel.updateRating(newRating)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Выберите год выпуска:")
        YearDropdown(selectedYear) { newYear ->
            viewModel.updateYear(newYear)
        }

        Button(
            onClick = {
                viewModel.saveFilters(selectedRating, selectedYear)
                navController.navigate("gamesList")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            Text(text = "Применить")
        }
    }
}

@Composable
fun RatingDropdown(selectedRating: Int?, onRatingSelected: (Int?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val ratingOptions = listOf(
        null to "Любой",
        5 to "Отличный (81-100)",
        4 to "Хороший (61-80)",
        3 to "Средний (41-60)",
        2 to "Плохой (21-40)",
        1 to "Очень плохой (0-20)"
    )

    Column {
        val selectedRatingText = ratingOptions.firstOrNull { it.first == selectedRating }?.second ?: "Любой"

        Text(
            text = selectedRatingText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable { expanded = true }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            ratingOptions.forEach { (rating, description) ->
                DropdownMenuItem(onClick = {
                    onRatingSelected(rating)
                    expanded = false
                }) {
                    Text(text = description)
                }
            }
        }
    }
}

@Composable
fun YearDropdown(selectedYearCategory: String?, onYearSelected: (String?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val yearOptions = listOf(
        null to "Любой",
        "2020-2024" to "2020-2024",
        "2010–2019" to "2010–2019",
        "2000–2009" to "2000–2009",
        "1990–1999" to "1990–1999"
    )

    Column {
        val selectedYearText = yearOptions.firstOrNull { it.first == selectedYearCategory }?.second ?: "Любой"
        Text(
            text = selectedYearText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable { expanded = true }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            yearOptions.forEach { (yearRange, description) ->
                DropdownMenuItem(onClick = {
                    onYearSelected(yearRange)
                    expanded = false
                }) {
                    Text(text = description)
                }
            }
        }
    }
}
