package com.tunahankaryagdi.dictionaryapp.presentation.screens.splash

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tunahankaryagdi.dictionaryapp.R
import com.tunahankaryagdi.dictionaryapp.presentation.navigation.NavigationItem
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {


    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,

                )
        )
        delay(2000)
        navController.navigate(NavigationItem.Home.route) {
            popUpTo(NavigationItem.Splash.route) {
                inclusive = true
            }
        }

    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Filled.Phone,
            contentDescription = stringResource(id = R.string.logo),
            tint = MaterialTheme.colors.primary,

        )

    }

}