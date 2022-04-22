package com.example.coroutinemodel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun sendNumbers() : Flow<Int> = flow {
    val primesList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primesList.forEach {
        delay(it * 100L)
        emit(it)
    }
}

//delay 없이 한번에 방출
fun sendNumbersTwo() = listOf(1, 2, 3, 4, 5).asFlow()

fun main() {
    runBlocking {
        println("Receiving Numbers")
        //함수 내에서 지정한 delay 만큼 리스트 값을 방출
        sendNumbersTwo().collect {
            println("Received Number : $it")
        }
        println("Finish!")
    }
}