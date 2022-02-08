package com.example.retrofitpractice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubClient {

    @GET("/users/{user}/repos")
    fun getRepos(@Path("user") user : String) : Call<List<GitHubRepo>>
}