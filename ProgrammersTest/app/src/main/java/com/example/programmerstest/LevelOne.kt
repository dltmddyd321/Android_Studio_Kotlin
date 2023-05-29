package com.example.programmerstest

import kotlin.math.max

fun main() {
    println(behindN("ProgrammerS123", 11))
}

fun morePlus(a: Int, b: Int): Int {
    val first = (a.toString() + b.toString()).toInt()
    val second = (b.toString() + a.toString()).toInt()
    return max(first, second)
}

fun behindN(my_string: String, n: Int): String {
    val length = my_string.toCharArray().size
    val check = length - n
    var res = ""

    my_string.toCharArray().forEachIndexed { index, c ->
        if (index >= check) res += c
    }

    return res
}