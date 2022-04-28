package com.example.coroutinemodel

import android.os.Handler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

//실무에서 사용한 여러 API Task를 효율적으로 처리하기 위한 최상위 부모 클래스 : 예시 활용할 것
abstract class ParentTask<T> {

    abstract fun execute() : T

    companion object {
        val headers : HashMap<String, String> = HashMap()
        val field : HashMap<String, String> = HashMap()
        val gson : Gson =
            GsonBuilder()
                .setLenient()
                .create()
    }

    fun execute(onResult : (T) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = execute()
            withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
                onResult(result)
            }
        }
    }

    fun execute(onResult : (T) -> Unit, handler: Handler?) {
        Executors.newSingleThreadExecutor().execute {
            val result = execute()
            if(handler != null) {
                handler.post {
                    onResult(result)
                }
            } else {
                onResult(result)
            }
        }
    }

    fun getRetrofit(baseUrl: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getClient() : OkHttpClient {
        val client : OkHttpClient
        val interceptor = HttpLoggingInterceptor()

        interceptor.apply {
            if(BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.BODY
            } else {
                this.level = HttpLoggingInterceptor.Level.NONE
            }
        }

        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        return client
    }
}