package com.example.singletontest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//SQLite 싱글톤
class SqlOpenHelperSingleton private constructor(context: Context): SQLiteOpenHelper(context, "MyDB", null, 1) {

    companion object {
        //자기 자신 변수 선언
        @Volatile private var instance: SqlOpenHelperSingleton ?= null

        //자기 자신 호출
        fun getInstance(context: Context) : SqlOpenHelperSingleton =
            //값이 비어있다면 자기 자신을 지칭
            instance ?: synchronized(this) {
                //이어서 값이 비어있다면 자기 자신을 다시 지칭
                instance ?: SqlOpenHelperSingleton(context).also {
                    instance = it
                }
            }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}