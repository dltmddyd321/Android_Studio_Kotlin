package com.example.roomexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginEntity (
    val userName : String,
    val password : String
        ){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}