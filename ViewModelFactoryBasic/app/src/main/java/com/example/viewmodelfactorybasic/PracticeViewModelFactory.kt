package com.example.viewmodelfactorybasic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PracticeViewModelFactory(private val counter: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PracticeViewModel(counter) as T
    }
}