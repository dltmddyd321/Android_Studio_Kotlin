package com.example.retrofitmvvmbasic.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PagingViewModelFactory(private val repository: PagingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return PagingViewModel(repository) as T
    }
}