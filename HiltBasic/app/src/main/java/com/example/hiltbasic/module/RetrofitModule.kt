package com.example.hiltbasic.module

import retrofit2.Retrofit

object RetrofitModule {

    fun provideAnalyticsService() : Service {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(Service::class.java)
    }
}

interface Service {

}