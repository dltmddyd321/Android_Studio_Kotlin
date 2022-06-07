package com.example.datastoremodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataStoreViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreRepository = DataStoreRepository(getApplication())

    val name = dataStoreRepository.getName.asLiveData()
    val age = dataStoreRepository.getAge.asLiveData()

    fun setName(name: String) {
        viewModelScope.launch {
            dataStoreRepository.setName(name)
        }
    }

    fun setAge(age: Int) {
        viewModelScope.launch {
            dataStoreRepository.setAge(age)
        }
    }
}