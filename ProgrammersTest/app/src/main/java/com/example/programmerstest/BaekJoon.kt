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
        if (it + 1 % 9 == 0) index++
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

fun main4() = with(Scanner(System.`in`)) {
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

fun main5() = with(Scanner(System.`in`)) {
    val string = next()
    var res = 0
    if (string.equals(string.reversed())) res = 1
    println(res)
}

fun main211() {
    val input = readLine()!!.split(" ")
    println(input[0].toLong() + input[1].toLong() + input[2].toLong())
}

fun main() = with(Scanner(System.`in`)) {
    val dial = arrayOf("ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ")
    val word = next().toString()
    var res = 0

    for (i in word.indices) {
        for (j in dial) {
            if (j.contains(word[i])) res += dial.indexOf(j) + 3
        }
    }
    println(res)
}

fun main323() = with(Scanner(System.`in`)) {
    val N = nextInt()
    val M = nextInt()
    val buckets = IntArray(N) { it + 1 } //1부터 N까지 담기

    for (time in 0 until M) {
        val i = nextInt()
        val j = nextInt()

        val temp = buckets[i - 1]
        buckets[i - 1] = buckets[j - 1]
        buckets[j - 1] = temp
    }

    buckets.forEach { print("$it ") }
}

fun main22() = with(Scanner(System.`in`)) {
    var res = ""
    val word = Array(5) { CharArray(15) { ' ' } }
    var maxSize = 0

    for (i in 0 until 5) {
        val input = next()
        maxSize = maxSize.coerceAtLeast(input.length)
        word[i] = input.toCharArray()
    }

    for (i in 0 until maxSize) {
        for (j in 0 until 5) {
            if (word[j].getOrNull(i) == null) continue
            res += word[j][i]
        }
    }
    println(res)
}

fun main6() = with(Scanner(System.`in`)) {
    val resArr = mutableListOf<Int>()
    val size = nextInt()
    val line = nextInt()

    repeat(size) { resArr.add(0) }

    repeat(line) {
        val start = nextInt()
        val end = nextInt()
        val num = nextInt()
        for (i in start - 1 until end) {
            resArr[i] = num
        }
    }

    println(resArr.joinToString(" "))
}

fun main7() = with(Scanner(System.`in`)) {
    val score = IntArray(5) { nextInt() }.apply { this.sortedDescending() }
    val avg = score.average()
    println(avg)
    println(score[2])
}

fun main8() = with(Scanner(System.`in`)) {
    data class Point(
        val x: Int,
        val y: Int
    )

    val arr = mutableListOf<Point>()
    val size = nextInt()
    repeat(size) {
        arr.add(Point(nextInt(), nextInt()))
    }
    val sortedArr = arr.sortedWith(compareBy(Point::x, Point::y))
    sortedArr.forEach { point ->
        println("${point.x} ${point.y}")
    }
}

fun main31() = with(Scanner(System.`in`)) {
    val stringBuilder = StringBuilder()

    val n = nextInt()
    val list = ArrayList<Int>()

    for (i in 0 until n) {
        list.add(nextInt())
    }

    list.sort()

    for (i in list) {
        stringBuilder.append(i).append("\n")
    }

    println(stringBuilder)
}

fun main34() = with(Scanner(System.`in`)) {

    fun parseGradeToScore(grade: String): Double {
        return when (grade) {
            "A+" -> {
                4.5
            }
            "A0" -> {
                4.0
            }
            "B+" -> {
                3.5
            }
            "B0" -> {
                3.0
            }
            "C+" -> {
                2.5
            }
            "C0" -> {
                2.0
            }
            "D+" -> {
                1.5
            }
            "D0" -> {
                1.0
            }
            "F" -> {
                0.0
            }
            else -> {
                -1.0
            }
        }
    }

    var sum = 0.0
    var sumScore = 0.0
    for (i in 0..19) {
        val subject = next()
        val score = nextDouble()
        val grade = next()
        val parsedGrade = parseGradeToScore(grade)
        if (parsedGrade != -1.0) {
            sum += parsedGrade * score
            sumScore += score
        }
    }
    println(sum / sumScore)
}

