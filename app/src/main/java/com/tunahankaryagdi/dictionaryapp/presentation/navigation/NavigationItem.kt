package com.tunahankaryagdi.dictionaryapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val name : String, val route :String, val icon : ImageVector) {

    object Splash : NavigationItem("Splash","splash",Icons.Filled.Phone)
    object Home : NavigationItem("Home","home", Icons.Filled.Home)
    object Add : NavigationItem("Add","add", Icons.Filled.Add)
}