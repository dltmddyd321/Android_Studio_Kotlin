package com.example.mvi_compose_basic.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mvi_compose_basic.R
import com.example.mvi_compose_basic.data.ScreenType

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = ScreenType.FIRST.name
    ) {
        composable(ScreenType.FIRST.name) {
            ScreenContent(titleId = R.string.first_screen)
        }
        composable(ScreenType.SECOND.name) {
            ScreenContent(titleId = R.string.second_screen)
        }
        composable(ScreenType.THIRD.name) {
            ScreenContent(titleId = R.string.third_screen)
        }
    }
}