package com.example.mvi_compose_basic

import kotlinx.coroutines.delay

class ExampleRepository {
    suspend fun sendMessage(text: String, onReady: (() -> Unit?)? = null) {
        delay(3000)
        onReady?.invoke()
    }
}