package com.example.coroutinemodel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

//delay 없이 한번에 동시 방출
fun thirdTest() = flowOf("One", "Two", "Three")

fun main() {
    runBlocking {
        println("Receiving Numbers")
        //원소가 7일 때 예외처리하고 플로우 종료
        (1..10).asFlow()
            .map { //각 원소가 방출되는 데에 300 delay 부여
                delay(300)
                it
            }
            .onEach {
                check(it != 7)
            }
            //onEach 조건이 없다면 catch는 발동하지 않음
            .catch { e ->
                println("Caught exception $e")
            }
            .onCompletion {
                println("Flow completed!")
            }
            .collect {
                println("Received Number $it")
            }
        println("Finish!")
    }
}

fun testOne() {
    runBlocking {
        println("Receiving Numbers")
        //함수 내에서 지정한 delay 만큼 리스트 값을 방출
        //collect를 명시하지 않으면 어떤 값도 출력되지 않음
        sendNumbersTwo().collect {
            println("Received Number : $it")
        }
        println("Finish!")
    }
}

fun testTwo() {
    runBlocking {
        println("Receiving Numbers")
        (1..10).asFlow()
            .map { //각 원소가 방출되는 데에 300 delay 부여
                delay(300)
                it
            }
            .filter { //조건에 맞는 원소를 방출시킨다. 단, 상위에서 자료형을 위한 명시 되어있을 것
                it % 2 == 0
            }
            .transform { //방출 시 형태 변환
                emit("Emitting string $it")
            }
            .collect {
                println("Received Number $it")
            }
        println("Finish!")
    }
}