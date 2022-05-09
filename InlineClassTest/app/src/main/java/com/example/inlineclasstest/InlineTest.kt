package com.example.inlineclasstest

import java.util.*

fun fnNormal(n1 : Int, n2 : Int) : Int {
    return n1 + n2
}

fun fnInline(n1 : Int, n2 : Int) : Int {
    return n1 + n2
}

//fun main() {
//    val result = fnNormal(1, 2)
//    println(result)
//}

//특정 타입에 별칭을 붙여 사용 가능
typealias UnixMillis = Long

fun UnixMillis.toCalendar() : Calendar {
    return Calendar.getInstance().also {
        it.timeInMillis = this
    }
}

@JvmInline
value class UnixMillisWrapper(private val millis: Long) {
    fun toCalendar() : Calendar {
        return Calendar.getInstance().also {
            it.timeInMillis = millis
        }
    }
}

fun main() {
    val unix : UnixMillis = System.currentTimeMillis()
    val calendar : Calendar = unix.toCalendar()
}

