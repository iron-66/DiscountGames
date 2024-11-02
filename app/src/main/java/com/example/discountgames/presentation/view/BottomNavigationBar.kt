package com.example.discountgames.presentation.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.discountgames.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation {
        BottomNavigationItem(
            selected = selectedRoute == "sales",
            onClick = { navController.navigate("gamesList") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_discount_24), contentDescription = null) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "settings",
            onClick = { navController.navigate("settings") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_settings_24), contentDescription = null) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "favorite",
            onClick = { navController.navigate("favorite") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_star_24), contentDescription = null) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "notifications",
            onClick = { navController.navigate("notifications") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_notifications_24), contentDescription = null) }
        )
    }
}
