package com.example.retrofitmvvmbasic

import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<TestInfo> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPostTwo(number : Int) : Response<TestInfo> {
        return RetrofitInstance.api.getPostTwo(number)
    }
}