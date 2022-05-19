package com.example.mvpbasic

import android.util.Log
import org.json.JSONObject

//Presenter에서 View와 데이터 간의 상호작용을 위한 로직이 실제 구현된다.
class Presenter(val view: Contractor.View, val repository: InfoRepository) : Contractor.Presenter {

    override fun initInfo() {
       repository.getInfo(object : InfoDataSource.LoadInfoCallback {
           override fun onInfoLoaded(info: JSONObject) {
               view.showInfo(info)
           }

           override fun onDataNotAvailable() {
               Log.d("RESULT", "DataNotAvailable!")
           }
       })
    }

    override fun setInfo(info: JSONObject) {
        view.showInfo(info)
    }

    override fun saveInfo(info: JSONObject) {
        repository.saveInfo(info)
    }
}