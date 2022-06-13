package com.example.hiltbasic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    var liveData = MutableLiveData("None Response")

    fun getData() = liveData

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val data = repository.checkInformation("")
            when (data.isSuccessful) {
                true -> {
                    liveData.postValue(data.body().toString())
                }
                else -> {
                    Log.d("FAILED", data.body().toString())
                }
            }
        }
    }
}