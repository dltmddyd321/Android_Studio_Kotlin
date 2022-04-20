package com.example.retrofitmvvmbasic

import retrofit2.Response
import retrofit2.http.GET

interface TestApi {
    @GET("posts/1")
    suspend fun getPost() : Response<TestInfo>
}