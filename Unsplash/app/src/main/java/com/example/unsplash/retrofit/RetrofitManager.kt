package com.example.unsplash.retrofit

import com.example.unsplash.utils.API

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    //Retrofit Interface 가져오기
    private val httpCall : RetrofitInterface ?= RetrofitClient.getClient(API.BASE_URL)?.create(RetrofitInterface::class.java)
}