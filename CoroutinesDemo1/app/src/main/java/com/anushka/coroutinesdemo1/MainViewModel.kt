package com.anushka.coroutinesdemo1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {
    //아래와 같은 보일러 플레이트 코드를 위해 viewModelScope 존재
//    private val job = Job()
//    private val scope = CoroutineScope(Dispatchers.IO + job)

    private var userRepository = UserRepository()
    var users: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData() {
        //MainDispatcherScope
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO) {
                result = userRepository.getUsers()
            }
            users.value = result
        }
    }

    //ViewModel 소멸 시 호출
//    override fun onCleared() {
//        super.onCleared()
//        job.cancel()
//    }
}