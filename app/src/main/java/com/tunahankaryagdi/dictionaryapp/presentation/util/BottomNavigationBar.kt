package com.tunahankaryagdi.dictionaryapp.presentation.util

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tunahankaryagdi.dictionaryapp.presentation.navigation.NavigationItem


@Composable
fun BottomNavigationBar(
    navController : NavController
) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Add
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = smallSize,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "${item.name} icon")},
                label = { Text(text = item.name)},
                selectedContentColor = MaterialTheme.colors.primary,
                selected = currentRoute == item.route,
                modifier = Modifier,
                unselectedContentColor = Color.Gray,
                onClick = {

                    navController.navigate(item.route){
                        popUpTo(NavigationItem.Home.route){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                }
            )
        }
    }
}
