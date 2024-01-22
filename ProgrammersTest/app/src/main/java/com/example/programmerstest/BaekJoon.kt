package com.example.programmerstest

import java.util.*
import kotlin.collections.ArrayList

fun main1(args: Array<String>) = with(Scanner(System.`in`)) {
    val a = nextInt()
    val b = nextInt()

    if (a > 0 && b > 0) println(1)
    if (a < 0 && b > 0) println(2)
    if (a < 0 && b < 0) println(3)
    if (a > 0 && b < 0) println(4)
}

fun main2(args: Array<String>) = with(Scanner(System.`in`)) {
    val a = nextInt()
    val bite = 4
    var res = ""

    repeat(a / bite) {
        res += "long "
    }

    println(res + "int")
}

fun main3(args: Array<String>) = with(Scanner(System.`in`)) {
    val allList = arrayListOf<ArrayList<Int>>()
    repeat(9) { allList.add(arrayListOf()) }
    var index = 0
    repeat(81) {
        allList[index].add(it)
        if (it + 1 % 9 == 0) index ++
    }

    val max = allList.flatten().max()

    allList.forEachIndexed { row, ints ->
        ints.forEachIndexed { index, i ->
            if (i == max) {
                println(90)
                println("${row + 1} ${index + 1}")
            }
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val grid = Array(9) { IntArray(9) }
    var maxNum = 0
    var maxRow = 0
    var maxCol = 0

    for (i in 0..8) {
        for (j in 0..8) {
            grid[i][j] = nextInt()

            if (grid[i][j] > maxNum) {
                maxNum = grid[i][j]
                maxRow = i
                maxCol = j
            }
        }
    }

    print("$maxNum\n${maxRow + 1} ${maxCol + 1}")
}