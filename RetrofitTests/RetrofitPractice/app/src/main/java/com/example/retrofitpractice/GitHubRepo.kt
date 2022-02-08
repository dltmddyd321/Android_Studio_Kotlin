package com.example.retrofitpractice

import com.google.gson.annotations.SerializedName

data class GitHubRepo (@SerializedName("name") val name: String)