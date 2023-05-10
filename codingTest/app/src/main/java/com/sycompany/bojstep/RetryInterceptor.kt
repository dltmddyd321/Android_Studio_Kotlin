package com.sycompany.bojstep

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RetryInterceptor: Interceptor {
    private var retryCount = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        if (response.code == 401) response = retryAfterTokenRefresh(request, chain)
        return response
    }

    private fun retryAfterTokenRefresh(request: Request, chain: Interceptor.Chain): Response {
        retryCount ++
        Thread.sleep(300L)
//        if (AppStatus.isTokenRefreshing && retryCount <= 3) retryAfterTokenRefresh(request, chain)
//        val retryRequest = request.newBuilder().header("x-auth-token", TimeBlocksUser.getInstance().authToken).build()
//        return chain.proceed(retryRequest)
        return chain.proceed(request)
    }
}