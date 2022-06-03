package com.example.hiltbasic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val list = arrayOf("A", "B", "C", "D", "E")

    val listChannel = Channel<String>()

    runBlocking {
        GlobalScope.launch {
            list.forEach {
                listChannel.send(it)
                //D까지 받은 다음 종료
                if (it == "D") listChannel.close()
            }
        }

        for (value in listChannel) {
            println("Value By Stream : $value")
        }
    }
}