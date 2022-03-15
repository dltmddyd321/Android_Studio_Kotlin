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

        fun getApi() : WikiMapApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(WikiMapApi::class.java)
        }
    }
}

interface WikiApi{
    //GET에 따른 쿼리문. value에 실제 쿼리 주소에 들어가는 항목 값을 입력 (String)
    @GET("api.php")
    fun hitCountCheck(
        @retrofit2.http.Query("action") action : String,
        @retrofit2.http.Query("format") format : String,
        @retrofit2.http.Query("list") list : String,
        @retrofit2.http.Query("srsearch") srsearch : String) : Single<Result>
        //네트워크 통신에서 성공시 JSON 데이터 하나를 받아오므로 Single<> 사용
}

interface WikiMapApi {
    @GET("api.php")
    fun hitCountCheck(
        @retrofit2.http.Query("action") action : String,
        @retrofit2.http.Query("format") format : String,
        @retrofit2.http.QueryMap options : Map<String, String>
    ) : Single<Result>
}

//변수명은 실제 가져올 JSON 파라미터의 이름과 일치해야 한다.
data class Result(val query: Query)
data class Query(val searchinfo: SearchInfo)
data class SearchInfo(val totalhits : Int, val suggestion: String)