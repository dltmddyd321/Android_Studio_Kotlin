package com.example.retrofitmvvmbasic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    //라이브 데이터를 담을 객체
    val myResponse : MutableLiveData<Response<TestInfo>> = MutableLiveData()
    val myResponseTwo : MutableLiveData<Response<TestInfo>> = MutableLiveData()
    val myCustomPosts : MutableLiveData<Response<List<TestInfo>>> = MutableLiveData()

    //통신에 대한 응답을 라이브 데이터로 처리
    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPostTwo(number : Int) {
        viewModelScope.launch {
            val response = repository.getPostTwo(number)
            myResponseTwo.value = response
        }
    }

    fun getCustomPosts(userId : Int){
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId)
            myCustomPosts.value = response
        }
    }
}