package com.sycompany.viewmodelfactorysecond

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val startTitle: String, private val startResult: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(startTitle, startResult) as T
        }
        throw IllegalArgumentException("Type anything useful here as exception")
    }
}