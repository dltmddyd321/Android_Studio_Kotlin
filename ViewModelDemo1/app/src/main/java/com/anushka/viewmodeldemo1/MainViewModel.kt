package com.anushka.viewmodeldemo1

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var count = 0

    fun getCurrentCnt(): Int {
        return count
    }

    fun getUpdateCnt(): Int {
        return ++count
    }
}