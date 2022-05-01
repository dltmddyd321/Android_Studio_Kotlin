package com.example.retrofitmvvmbasic.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class PagingViewModel(private val repository: PagingRepository) : ViewModel() {

    private val myCustomPosts : MutableLiveData<Int> = MutableLiveData()

    //라이브 데이터 변경 시 다른 라이브 데이터 발행
    val result = myCustomPosts.switchMap { queryString ->
        repository.getPost(queryString).cachedIn(viewModelScope)
    }

    fun searchPost(userId : Int) {
        myCustomPosts.value = userId
    }
}