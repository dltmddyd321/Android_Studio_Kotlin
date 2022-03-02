package com.example.wikiapi

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Http Response Code 받기 위해 Single<> 사용
interface WikiApiService {
    @GET("api.php")
    fun hitCountCheck(@Query("action") action : String,
                      @Query("format") format : String,
                      @Query("list") list : String,
                      @Query("srsearch") srsearch : String) :
                        Observable<WikiModels.Result>

    @GET("api.php")
    fun hitCountWithResponseCode(@Query("action") action : String,
                      @Query("format") format : String,
                      @Query("list") list : String,
                      @Query("srsearch") srsearch : String) :
                        Single<Response<WikiModels.Result>>

    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()
        fun create() : WikiApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://en.wikipedia.org/w/")
                .build()
            return retrofit.create(WikiApiService::class.java)
        }
    }
}

object WikiModels {
    data class Result(val query : Query)
    data class Query(val searchInfo : SearchInfo)
    data class SearchInfo(val totalHits : Int)
}

