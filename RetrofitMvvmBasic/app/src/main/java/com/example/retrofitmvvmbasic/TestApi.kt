package com.example.retrofitmvvmbasic

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TestApi {
    @GET("posts/1")
    suspend fun getPost() : Response<TestInfo>

    @GET("posts/{postNumber}")
    suspend fun getPostTwo(
        @Path("postNumber") number : Int
    ) : Response<TestInfo>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId : Int
    ): Response<List<TestInfo>>
}