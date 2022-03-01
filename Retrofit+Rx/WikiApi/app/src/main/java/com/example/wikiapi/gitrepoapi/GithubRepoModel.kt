package com.example.wikiapi.gitrepoapi

import com.google.gson.annotations.SerializedName

class GithubRepoModel {
        @SerializedName("id")
        val id: Long = 0

        @SerializedName("name")
        val name: String = ""

        @SerializedName("full_name")
        val fullName: String = ""
}