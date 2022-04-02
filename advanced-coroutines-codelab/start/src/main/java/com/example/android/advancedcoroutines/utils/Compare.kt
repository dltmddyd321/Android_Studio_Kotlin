package com.example.android.advancedcoroutines.utils

class Compare(val width : Int, val height : Int) : Comparable<Compare> {

    val area = width * height

    override fun compareTo(other: Compare): Int =
        when {
            this.area > other.area -> 1
            this.area < other.area -> -1
            else -> 0
        }
}

fun main() {
    val one = Compare(3,5)
    val two = Compare(3,5)
    val three = Compare(3,5)

    println(one >= three)
    println(one < three)
    println(two > three)
}