package com.example.hiltbasic

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("settings/functions/information")
    suspend fun getInformation(
        @Query("functionName") functionName : String
    ) : Response<InformationResult>
}