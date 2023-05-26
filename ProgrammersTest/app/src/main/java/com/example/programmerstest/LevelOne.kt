package com.example.programmerstest

import kotlin.math.max

fun main() {
    println(morePlus(9, 91))
}

fun morePlus(a: Int, b: Int): Int {
    val first = (a.toString() + b.toString()).toInt()
    val second = (b.toString() + a.toString()).toInt()
    return max(first, second)
}