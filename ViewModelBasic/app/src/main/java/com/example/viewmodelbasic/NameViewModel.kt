package com.example.viewmodelbasic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
currentName 이라는 변수가 값을 Observe할 대상으로 만들어질 것이다.
여기에서 MutableLiveData 라는 클래스가 쓰였는데, 이 클래스는 Observer Pattern과 비슷한 역할을 가지는 클래스이다.
ViewModel에서 사용하는 데이터는 이 클래스의 생성자를 통해 만든다고 이해하면 된다.
 */
class NameViewModel : ViewModel() {
    val currentName : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}