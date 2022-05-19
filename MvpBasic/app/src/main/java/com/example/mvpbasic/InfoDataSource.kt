package com.example.mvpbasic

import org.json.JSONObject

interface InfoDataSource {

    //데이터 호출 시 특정 상황 분기 처리 인터페이스
    interface LoadInfoCallback {
        fun onInfoLoaded(info: JSONObject)

        fun onDataNotAvailable()
    }

    //데이터를 호출
    fun getInfo(callback: LoadInfoCallback)

    //데이터를 저장
    fun saveInfo(info: JSONObject)
}