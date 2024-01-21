package com.example.mvi_compose_basic.data

data class ExampleState(
    val text: String = "",
    val isSending: Boolean = false,
    val result: String? = null
)


