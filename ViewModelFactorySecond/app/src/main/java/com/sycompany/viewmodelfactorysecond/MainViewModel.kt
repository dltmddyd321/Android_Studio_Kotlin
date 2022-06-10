package com.sycompany.viewmodelfactorysecond

import androidx.lifecycle.ViewModel

class MainViewModel(startTitle: String, startResult: Int) : ViewModel() {

    private var title = startTitle
    private var result = startResult

    fun getTitle() : String {
        return title
    }

    fun getResult() : Int {
        return result
    }

    fun setPlus(input: Int) {
        result += input
    }

    fun setMinus(input: Int) {
        result -= input
    }
}