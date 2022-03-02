package com.example.wikiapi.gitrepoapi

import com.google.gson.annotations.SerializedName

class GithubResponseModel {
    @SerializedName("total_count")
    val totalCount : Int = 0

    @SerializedName("items")
    val items: List<GithubRepoModel> = listOf()

}