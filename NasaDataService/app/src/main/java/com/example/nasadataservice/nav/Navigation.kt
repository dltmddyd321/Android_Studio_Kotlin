package com.example.nasadataservice.nav

import androidx.navigation.NavController

object Destinations {
    const val Home = "home"
    const val Manifest = "manifest"
}

//Nav 동작 클래스
class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Destinations.Home) }
    val manifest: (roverName: String) -> Unit = { roverName ->
        navController.navigate("manifest/${roverName}")
    }
}