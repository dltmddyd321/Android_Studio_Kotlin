package com.example.servicestylerx

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

open class PeriodApiTask {

    companion object {
        private val gson =
            GsonBuilder()
                .setLenient()
                .create()

        private var client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        fun getApi() : PeriodApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://img.gettimeblocks.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(PeriodApi::class.java)
        }
    }
}

interface PeriodApi {
    @GET("maintenance/limitedPeriodOfServiceProvision.json")
    fun getPeriod() : Single<Response<Date>>
}

data class Date(val limit_date : String)