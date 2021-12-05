package com.example.kotlinbasic

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.TestOnly
import kotlin.system.measureTimeMillis

class Coroutine {
    @TestOnly
    fun test01() = runBlocking {
        val time = measureTimeMillis {
            val name = getFirstName()
            val lastName = getLastName()

            print("Hello, $name$lastName")
        }
    }

    suspend fun getFirstName() : String {
        delay(1000)
        return "이"
    }

    suspend fun getLastName() : String {
        delay(1000)
        return "승용"
    }
}