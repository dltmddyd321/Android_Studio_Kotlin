package com.example.unsplash

import com.example.unsplash.models.PhotoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Query

object Repository {

    private val unsplashApiService : UnsplashApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Url.UNSPLASH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkhttpCilent())
            .build()
            .create()
    }

    suspend fun getRandomPhotos(query: String?) : List<PhotoResponse> ?=
        unsplashApiService.getRandomPhotos(query).body()

    private fun buildOkhttpCilent() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if(BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()
}