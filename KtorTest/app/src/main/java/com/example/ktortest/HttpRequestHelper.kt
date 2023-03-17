package com.example.ktortest

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HttpRequestHelper {
    private val client: HttpClient = HttpClient(CIO)

    suspend fun requestKtorIo(): String =
        withContext(Dispatchers.IO) {
            val url = "https://cat-fact.herokuapp.com/facts/random"

            val response: HttpResponse = client.get(url)
            val responseStatus = response.status

            if (responseStatus == HttpStatusCode.OK) {
                Log.d("HttpRequestHelper", "requestKtorResponse: ${response.readText()}") //받아온 data 로그
                response.readText()
            } else {
                "error: $responseStatus"
            }
        }
}