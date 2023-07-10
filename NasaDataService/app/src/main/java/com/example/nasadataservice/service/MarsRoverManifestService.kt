package com.example.nasadataservice.service

import com.example.nasadataservice.BuildConfig
import com.example.nasadataservice.service.model.RoverManifestRemoteModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MarsRoverManifestService {
//photos?sol=1000&api_key=DEMO_KEY
    @GET("mars-photos/api/v1/rovers/{rover_name}/?sol=10&api_key=$DEMO_KEY")
    suspend fun getMarsRoverManifest(@Path("rover_name") roverName: String): RoverManifestRemoteModel

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
        private const val DEMO_KEY = "52mHwkAI7LHWh8ExbPZa0MErsTOez9OThVZdlRBH"

        fun create(): MarsRoverManifestService {
            val logger = HttpLoggingInterceptor()
            logger.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarsRoverManifestService::class.java)
        }
    }
}