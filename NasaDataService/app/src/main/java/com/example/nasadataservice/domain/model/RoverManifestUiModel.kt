package com.example.nasadataservice.domain.model

sealed class RoverManifestUiState{
    data class Success(
        val roverManifestUiModel: List<RoverManifestUiModel>
    ): RoverManifestUiState()
    object Loading: RoverManifestUiState()
    object Error: RoverManifestUiState()
}

data class RoverManifestUiModel(
    val sol: String,
    val earthDate: String,
    val photoNumber: String
) : Comparable<RoverManifestUiModel> {
    override fun compareTo(other: RoverManifestUiModel): Int =
        other.earthDate.compareTo(this.earthDate)
}