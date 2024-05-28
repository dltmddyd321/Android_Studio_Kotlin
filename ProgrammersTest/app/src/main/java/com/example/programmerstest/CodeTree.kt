package com.example.programmerstest

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val arr = mutableMapOf<Int, Int>()

    repeat(n) {
        val k = scanner.nextInt()
        arr[k] = (arr[k] ?: 0) + 1
    }

    val keysWithValueOne = arr.filter { it.value == 1 }.keys.toList()

    if (keysWithValueOne.isEmpty()) {
        println(-1)
        return
    }

    val maxKey = keysWithValueOne.maxByOrNull { it }

    println(if (keysWithValueOne.count { it == maxKey } == 1) {
        maxKey ?: -1
    } else {
        -1
    })
}