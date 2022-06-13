package com.example.hiltbasic

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun checkInformation(param: String) =
        apiService.getInformation(param)
}