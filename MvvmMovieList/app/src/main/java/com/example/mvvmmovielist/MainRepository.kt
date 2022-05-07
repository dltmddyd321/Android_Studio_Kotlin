package com.example.mvvmmovielist

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}