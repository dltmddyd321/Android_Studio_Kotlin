package com.example.postservice.di

import com.example.postservice.BuildConfig
import com.example.postservice.api.SweetTrackerApi
import com.example.postservice.api.Url
import com.example.postservice.db.AppDatabase
import com.example.postservice.repository.TrackingItemRepository
import com.example.postservice.repository.TrackingItemRepositoryImpl
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    //single : 한 번만 객체 생성
    single { Dispatchers.IO }

    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().trackingItemDao() }

    single { OkHttpClient()
        .newBuilder()
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

    single<SweetTrackerApi> {
        Retrofit.Builder().baseUrl(Url.SWEET_TRACKER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    single<TrackingItemRepository> { TrackingItemRepositoryImpl(get(), get(), get()) }
}