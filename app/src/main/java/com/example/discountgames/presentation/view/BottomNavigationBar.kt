package com.example.discountgames.presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.discountgames.R
import com.example.discountgames.data.BadgeCache

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
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = null
                )

                Log.d("BottomNavigationBar", "Badge visibility: ${BadgeCache.showBadge}")
                if (BadgeCache.showBadge.value) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color.Yellow, CircleShape)
                            .offset(x = 15.dp, y = (-5).dp)
                    )
                }
            }
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
