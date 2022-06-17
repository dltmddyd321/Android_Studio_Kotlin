package com.sycompany.moshibasic

import retrofit2.Call
import retrofit2.http.GET

interface AdviceService {
    @GET("advice")
    fun getAdvice() : Call<Advice>

    @GET("advice")
    suspend fun getAdviceSuspend() : Advice

    companion object {
        const val BASE_URL = "https://api.adviceslip.com/"
    }
}