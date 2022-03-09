package com.example.viewmodeltutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//데이터의 변경
//뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있다.
class NumberViewModel : ViewModel() {

    companion object {
        const val TAG : String = "ViewModelLog"
    }
    private val _currentValue = MutableLiveData<Int>()

    //변경되지 않는 데이터
    //값을 직접 라이브 데이터에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정
    val currentValue : LiveData<Int>
        get() = _currentValue

    init {
        Log.d(TAG, "생성자 호출")
        _currentValue.value = 0
    }


}