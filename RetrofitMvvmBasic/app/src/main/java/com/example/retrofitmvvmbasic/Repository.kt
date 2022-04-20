package com.example.retrofitmvvmbasic

import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<TestInfo> {
        return RetrofitInstance.api.getPost()
    }
}