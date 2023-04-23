package com.example.mvvminstagramclone.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var id: MutableLiveData<String> = MutableLiveData("")
    var pw: MutableLiveData<String> = MutableLiveData("")
    var showInputNumberActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    var showFindIdActivity: MutableLiveData<Boolean> = MutableLiveData(false)


}