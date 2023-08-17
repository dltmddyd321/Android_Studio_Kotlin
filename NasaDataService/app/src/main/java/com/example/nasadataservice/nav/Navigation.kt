package com.example.nasadataservice.nav

import androidx.navigation.NavController

object Destinations {
    const val Home = "home"
    const val Manifest = "manifest/{roverName}"
    const val Photo = "photo/{roverName}?sol={sol}"
}

//Nav 동작 클래스
class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Destinations.Home) }
    val manifest: (roverName: String) -> Unit = { roverName ->
        navController.navigate("manifest/${roverName}")
    }
    val photo: (roverName: String, sol: String) -> Unit =
        { roverName, sol ->
            navController.navigate("photo/${roverName}?sol=${sol}")
        }
}