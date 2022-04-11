package com.example.contacts

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao() : ContactDao

    companion object {
        private var INSTANCE : ContactDatabase ?= null

        fun getInstance(context: Context) : ContactDatabase? {
            if(INSTANCE == null) {
                //여러 스레드가 접근하지 못하도록 synchronized
                synchronized(ContactDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ContactDatabase::class.java, "contact")
                        //DB 갱신 시 기존의 테이블을 버리고 새로 사용
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}