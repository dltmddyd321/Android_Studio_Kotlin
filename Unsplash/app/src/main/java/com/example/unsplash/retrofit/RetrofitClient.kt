package com.example.unsplash.retrofit

import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.unsplash.App
import com.example.unsplash.utils.API
import com.example.unsplash.utils.Constants
import com.example.unsplash.utils.isJsonArray
import com.example.unsplash.utils.isJsonObject
import com.google.gson.JsonObject
import okhttp3.Handshake
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Handler

object RetrofitClient {

    private var retrofitClient : Retrofit ?= null

    fun getClient(baseUrl : String) : Retrofit? {
        Log.d(Constants.TAG, "RetrofitClient - getClient() called")

        val client = OkHttpClient.Builder()

        //로그를 찍기위한 Interceptor
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(Constants.TAG, "RetrofitClient - Log() called / msg : $message")

                when {
                    message.isJsonObject() ->
                        Log.d(Constants.TAG, JSONObject(message).toString(4))
                    message.isJsonArray() ->
                        Log.d(Constants.TAG, JSONObject(message).toString(4))
                    else -> {
                        try {
                            Log.d(Constants.TAG, JSONObject(message).toString(4))
                        } catch (e: Exception) {
                            Log.d(Constants.TAG, message)
                        }
                    }
                }

            }

        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        //설정한 Logging Interceptor 추가
        client.addInterceptor(loggingInterceptor)

        //Basic parameter interceptor setting
        val baseParameterInterceptor : Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain) : Response{
                Log.d(Constants.TAG, "RetrofitClient - intercept() called")
                val originalRequest = chain.request()

                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()
                val finalRequest = originalRequest
                    .newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                //return chain.proceed(finalRequest)

                val response = chain.proceed(finalRequest)

                if(response.code != 200) {

                    //UI Thread Go
                    android.os.Handler(Looper.getMainLooper()).post {
                        Toast.makeText(App.instance, "${response.code} Error!", Toast.LENGTH_SHORT).show()
                    }
                }

                return response
            }
        })

        client.addInterceptor(baseParameterInterceptor)

        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        if(retrofitClient == null)
        {
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofitClient
    }
}