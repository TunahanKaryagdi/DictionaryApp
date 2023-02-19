package com.tunahankaryagdi.dictionaryapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tunahankaryagdi.dictionaryapp.presentation.screens.main.MainScreen
import com.tunahankaryagdi.dictionaryapp.presentation.theme.AddScreen
import com.tunahankaryagdi.dictionaryapp.presentation.navigation.NavigationItem
import com.tunahankaryagdi.dictionaryapp.presentation.screens.splash.SplashScreen


@Composable
fun Navigation(navController : NavHostController) {

    NavHost(navController = navController, startDestination = NavigationItem.Splash.route){

        
        composable(NavigationItem.Splash.route){
            SplashScreen(navController = navController)
        }

        composable(NavigationItem.Home.route){
            MainScreen()
        }

        composable(NavigationItem.Add.route){
            AddScreen(navController =navController)
        }

        
    }

}