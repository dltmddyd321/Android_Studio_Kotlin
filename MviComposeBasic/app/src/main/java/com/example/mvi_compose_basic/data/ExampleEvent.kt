package com.example.mvi_compose_basic.data

sealed class ExampleEvent {
    data class TextChanged(val text: String) : ExampleEvent()
    object SendClicked : ExampleEvent()
    object MessageShown : ExampleEvent()
}
