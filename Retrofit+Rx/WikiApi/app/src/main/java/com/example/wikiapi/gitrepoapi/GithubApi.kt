package com.example.wikiapi.gitrepoapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class GithubApi {

    interface GithubApi {
        @GET("/search/repositories")
        fun getRepoList(@Query("q") query: String) : Observable<GithubResponseModel>
    }

    companion object {
        fun getRepoList(query: String): Observable<GithubResponseModel> {
            return RetrofitCreator.create(GithubApi::class.java).getRepoList(query)
        }
    }
}