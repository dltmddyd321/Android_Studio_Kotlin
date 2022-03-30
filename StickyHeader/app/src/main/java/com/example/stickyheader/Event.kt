package com.example.stickyheader

data class Event(
    val value : Int
) {
    //data Class 요소의 get() 초기값 선언
    val date : Int
        get() = value / 10

    val isHeader : Boolean
        get() = value % 10 == 0
}