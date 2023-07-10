package com.example.nasadataservice.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.nasadataservice.ui.manifest.MarsRoverManifestViewModel

@Composable
fun ManifestScreen(
    roverName: String?,
    marsRoverManifestViewModel: MarsRoverManifestViewModel
) {
    if (!roverName.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            marsRoverManifestViewModel.getMarsRoverManifest(roverName)
        }
    }
    Text(text = roverName ?: "")
}

@Preview
@Composable
fun ManifestScreenPreview() {
//    ManifestScreen("TEST")
}