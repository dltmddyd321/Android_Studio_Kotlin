package com.example.retrofitmvvmbasic

import com.google.gson.annotations.SerializedName

data class TestInfo(
    @SerializedName("userId")
    val myUserId : Int,
    val id : Int,
    val title : String,
    val body : String
)