package com.example.discountgames

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.discountgames.R

@Composable
fun BottomNavigationBar(navController: NavController, selectedRoute: String) {
    BottomNavigation {
        BottomNavigationItem(
            selected = selectedRoute == "sales",
            onClick = { navController.navigate("gamesList") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_discount_24), contentDescription = null) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "newGames",
            onClick = { navController.navigate("newGames") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = null) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "freeGames",
            onClick = { navController.navigate("freeGames") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_shopping_cart_24), contentDescription = null) }
        )
        BottomNavigationItem(
            selected = selectedRoute == "notifications",
            onClick = { navController.navigate("notifications") },
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_notifications_24), contentDescription = null) }
        )
    }
}
