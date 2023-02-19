package com.tunahankaryagdi.dictionaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tunahankaryagdi.dictionaryapp.presentation.navigation.NavigationItem
import com.tunahankaryagdi.dictionaryapp.presentation.screens.main.MainScreen
import com.tunahankaryagdi.dictionaryapp.presentation.theme.DictionaryAppTheme
import com.tunahankaryagdi.dictionaryapp.presentation.util.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(

                        bottomBar = {
                            if (getCurrentRoute(navController = navController) != NavigationItem.Splash.route) {
                                BottomNavigationBar(navController)
                            }
                        },
                        content = {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = it.calculateBottomPadding())
                            ) {
                                Navigation(navController = navController)

                            }

                        },

                        )


                }
            }
        }
    }
}

@Composable
fun getCurrentRoute(navController: NavController): String? {
    return navController.currentBackStackEntryAsState().value?.destination?.route
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DictionaryAppTheme {
        BottomNavigationBar(rememberNavController())
    }
}