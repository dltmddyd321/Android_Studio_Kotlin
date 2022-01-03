package com.example.unsplash.retrofit

import com.example.unsplash.utils.API
import com.example.unsplash.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    //Retrofit Interface 가져오기
    private val httpCall : RetrofitInterface ?= RetrofitClient.getClient(API.BASE_URL)?.create(RetrofitInterface::class.java)

    fun searchPhotos(searchTerm : String?, completion: (RESPONSE_STATE, String) -> Unit) {

        val term = searchTerm.let {
            it
        }?: ""

        val call = httpCall?.searchPhotos(searchTerm = term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                when(response.code()) {
                    200 -> {
                        completion(RESPONSE_STATE.OKAY, response.body().toString())
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                //에러에 대한 확인 전송
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }
}