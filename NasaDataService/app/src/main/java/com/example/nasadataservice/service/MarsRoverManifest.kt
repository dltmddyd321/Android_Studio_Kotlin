package com.example.nasadataservice.service

import com.example.nasadataservice.service.model.RoverManifestRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MarsRoverManifest {

    @GET("mars-photos/api/v1/manifest/{rover_name}?api_key=DEMO_KEY")
    suspend fun getMarsRoverManifest(@Path("rover_name") roverName: String): RoverManifestRemoteModel

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
    }
}