package com.example.servicestylerx

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit.*

open class RxApiTask {

    companion object {
        private val gson =
            GsonBuilder()
                .setLenient()
                .create()

        private var client = OkHttpClient.Builder()
            .connectTimeout(1, MINUTES)
            .readTimeout(1, MINUTES)
            .writeTimeout(15, SECONDS)
            .build()

        fun getApi() : WikiApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(WikiApi::class.java)
        }
    }
}

interface WikiApi{
    @GET("api.php")
    fun hitCountCheck(
        @retrofit2.http.Query("action") action : String,
        @retrofit2.http.Query("format") format : String,
        @retrofit2.http.Query("list") list : String,
        @retrofit2.http.Query("srsearch") srsearch : String) :
            Single<Result>
}

data class Result(val query: Query)
data class Query(val searchinfo: SearchInfo)
data class SearchInfo(val totalhits : Int, val suggestion: String)