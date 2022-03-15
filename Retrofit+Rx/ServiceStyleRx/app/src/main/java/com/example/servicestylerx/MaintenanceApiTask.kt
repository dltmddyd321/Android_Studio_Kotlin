package com.example.servicestylerx

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import timber.log.Timber
import java.util.concurrent.TimeUnit

open class MaintenanceApiTask {

    companion object {
        private const val testLang = "ko"

        private val gson =
            GsonBuilder()
                .setLenient()
                .create()

        private var client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        fun getApi() : MaintenanceApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://img.gettimeblocks.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(MaintenanceApi::class.java)
        }

        @SuppressLint("CheckResult")
        fun checkMaintenance() {
            getApi().getMaintenance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Timber.d("결과 : $result")
                        if(!result.active) {
                            when(testLang) {
                                "ko" -> Timber.d("한국 : ${result.ko.title}")
                                else -> Timber.d("Nothing")
                            }
                        }
                    }, { error ->
                        Timber.d("Error -> $error")
                    }
                )
        }
    }
}

interface MaintenanceApi {
    @GET("maintenance/maintenance.json")
    fun getMaintenance() : Single<Main>
}

data class Main(val ko : Kor, val ch : Chi, val jp : Jap, val active : Boolean,  val en : Eng, val type : Int)
data class Kor(val title : String, val content : String)
data class Jap(val title : String, val content : String)
data class Chi(val title : String, val content : String)
data class Eng(val title : String, val content : String)

