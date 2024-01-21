package com.example.mvi_compose_basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_compose_basic.data.ExampleEvent
import com.example.mvi_compose_basic.data.ExampleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExampleViewModel(
    private val repository: ExampleRepository = ExampleRepository()
) : ViewModel() {
    private val _state = MutableStateFlow(ExampleState())
    val state = _state.asStateFlow()

    fun handleEvent(event: ExampleEvent, onReady: (() -> Unit?)? = null) {
        when (event) {
            is ExampleEvent.SendClicked -> sendClicked { onReady?.invoke() }
            is ExampleEvent.MessageShown -> messageShown()
            is ExampleEvent.TextChanged -> textChanged(event)
        }
    }

    private fun sendClicked(onReady: (() -> Unit?)? = null) {
        _state.update { state -> state.copy(isSending = true) }
        viewModelScope.launch {
            //동기적으로 처리 진행
            repository.sendMessage(state.value.text, onReady)
            _state.update {
                it.copy(
                    text = "",
                    isSending = false,
                    result = "Success!"
                )
            }
        }
    }

    private fun textChanged(data: ExampleEvent.TextChanged) {
        _state.update { state -> state.copy(text = data.text) }
    }

    private fun messageShown() {
        _state.update { state -> state.copy(result = null) }
    }
}