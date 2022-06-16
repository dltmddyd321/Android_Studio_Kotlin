package com.example.stateflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow<>()

    sealed class LoginUiState {
        object Success : LoginUiState()
        data class Error(val msg: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }
}