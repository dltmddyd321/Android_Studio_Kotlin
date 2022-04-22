package com.example.retrofitmvvmbasic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//ViewModel에서 파라미터로 Repository를 받기 위함
class ViewModelFactory(private val repository : Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}