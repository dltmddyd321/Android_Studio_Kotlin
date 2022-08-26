package com.sycompany.viewmodeltimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    //Set
    private val _timerCnt = MutableLiveData<Int>()

    //get
    val timerCnt : LiveData<Int>
        get() = _timerCnt

    private lateinit var a : Job

    init {
        _timerCnt.value = 10

    }

    fun timerStart() {
        if (::a.isInitialized) a.cancel()
        _timerCnt.value = 10
        a = viewModelScope.launch {
            while (_timerCnt.value!! > 0) {
                _timerCnt.value = _timerCnt.value!!.minus(1)
                delay(1000L)
            }
        }
    }

    fun timerStop() {
        if (::a.isInitialized) a.cancel()
    }
}