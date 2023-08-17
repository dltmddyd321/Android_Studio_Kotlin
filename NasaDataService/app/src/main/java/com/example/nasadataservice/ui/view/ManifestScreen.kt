package com.example.nasadataservice.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nasadataservice.domain.model.RoverManifestUiState
import com.example.nasadataservice.ui.manifest.MarsRoverManifestViewModel

@Composable
fun ManifestScreen(
    roverName: String?,
    marsRoverManifestViewModel: MarsRoverManifestViewModel,
    onClick: (roverName: String, sol: String) -> Unit
) {
    val viewState by marsRoverManifestViewModel.roverManifestUiState.collectAsStateWithLifecycle()

    if (!roverName.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            marsRoverManifestViewModel.getMarsRoverManifest(roverName)
        }

        when (val roverManifestUiState = viewState) {
            RoverManifestUiState.Error -> Error()
            RoverManifestUiState.Loading -> Loading()
            is RoverManifestUiState.Success -> ManifestList(roverManifestUiModelList = roverManifestUiState.roverManifestUiModel, roverName, onClick)
        }
    }
    Text(text = roverName ?: "")
}

@Preview
@Composable
fun ManifestScreenPreview() {
//    ManifestScreen("TEST")
}