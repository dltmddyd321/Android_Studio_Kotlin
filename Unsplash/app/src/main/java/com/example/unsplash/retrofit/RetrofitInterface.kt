package com.example.unsplash.retrofit

import com.example.unsplash.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET(API.SEARCH_PHOTO)
    fun searchPhotos(@Query("query") searchTerm : String) : Call<JsonElement>

    @GET(API.SEARCH_USER)
    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>
}