package com.example.viewmodelfactorybasic

import androidx.lifecycle.ViewModel

class PracticeViewModel(initialise: Int) : ViewModel() {
    var cnt = initialise
    fun increment() {
        cnt ++
    }
}