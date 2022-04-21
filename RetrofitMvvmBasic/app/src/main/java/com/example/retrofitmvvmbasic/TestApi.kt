package com.example.retrofitmvvmbasic

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi {
    @GET("posts/1")
    suspend fun getPost() : Response<TestInfo>

    @GET("posts/{postNumber}")
    suspend fun getPostTwo(
        @Path("postNumber") number : Int
    ) : Response<TestInfo>
}