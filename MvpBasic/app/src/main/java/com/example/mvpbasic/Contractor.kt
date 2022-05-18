package com.example.mvpbasic

import org.json.JSONObject

//View와 Presenter의 인터페이스 정의
interface Contractor {

    interface View {
        fun showInfo(info: JSONObject)
    }

    interface Presenter {
        //App이 시작될 때, 만일 저장된 데이터가 있다면 가져온다.
        fun initInfo()

        //데이터를 TextView에 출력할 수 있도록 View에게 데이터 출력을 요청한다.
        fun setInfo(info: JSONObject)

        //View의 EditText로부터 가져온 데이터를 Model에게 저장시킨다.
        fun saveInfo(info: JSONObject)
    }
}