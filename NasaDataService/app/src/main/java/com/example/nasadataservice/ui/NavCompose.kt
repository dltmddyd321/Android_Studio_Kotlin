package com.example.nasadataservice.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nasadataservice.nav.Action
import com.example.nasadataservice.nav.Destinations.Home
import com.example.nasadataservice.nav.Destinations.Manifest
import com.example.nasadataservice.ui.manifest.MarsRoverManifestViewModel
import com.example.nasadataservice.ui.theme.NasaDataServiceTheme
import com.example.nasadataservice.ui.view.ManifestScreen
import com.example.nasadataservice.ui.view.RoverList

@Composable
fun NavCompose() {
    val navController = rememberNavController()
    val actions = remember(navController) {
        Action(navController)
    }

    //Nav 화면 구성
    NasaDataServiceTheme {
        NavHost(navController = navController, startDestination = Home) {
            composable(Home) {
                RoverList { roverName ->
                    actions.manifest(roverName)
                }
            }
            composable(Manifest) {
                ManifestScreen(
                    roverName = it.arguments?.getString("roverName"),
                    marsRoverManifestViewModel = hiltViewModel()
                )
            }
        }
    }
}