package com.example.nasadataservice.data

import com.example.nasadataservice.domain.model.RoverManifestUiState
import com.example.nasadataservice.domain.model.toUiModel
import com.example.nasadataservice.service.MarsRoverManifestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarsRoverManifestRepo @Inject constructor(
    private val marsRoverManifestService: MarsRoverManifestService
) {
    fun getMarsRoverManifest(roverName: String): Flow<RoverManifestUiState> = flow {
        try {
            emit(
                toUiModel(
                    marsRoverManifestService.getMarsRoverManifest(
                        roverName.lowercase()
                    )
                )
            )
        } catch (t: Throwable) {
            emit(RoverManifestUiState.Error)
        }
    }
}