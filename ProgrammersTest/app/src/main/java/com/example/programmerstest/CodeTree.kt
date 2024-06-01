package com.example.programmerstest

import java.lang.StringBuilder
import java.util.Scanner
import java.util.Stack

fun main() {}

fun solution(numbers: IntArray, hand: String): String {
    val phoneArray = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("*", "0", "#")
    )

    var res = StringBuilder()

    val leftNum = phoneArray.map { it[0] }
    val rightNum = phoneArray.map { it[2] }

    numbers.forEach {
        val value = it.toString()
        if (value in leftNum) {
            res.append("L")
        } else if (value in rightNum) {
            res.append("R")
        } else {
            //TODO: 손가락 거리 별 계산 처리
        }
    }
    return res.toString()
}

fun solution11(board: Array<IntArray>, moves: IntArray): Int {
    var res = 0
    val stack = Stack<Int>()

    val newBoard = mutableListOf<MutableList<Int>>()
    board.forEach { newBoard.add(it.toMutableList()) }

    fun getLines(index: Int): List<Int> {
        return newBoard.map { it[index] }
    }

    moves.forEach {
        val lines = getLines(it - 1)
        val target = lines.firstOrNull { num -> num != 0 } ?: 0
        if (target != 0) {
            val pos = lines.indexOfFirst { value -> value == target }
            newBoard[pos][it - 1] = 0
            if (stack.isNotEmpty() && stack.peek() == target) {
                stack.pop()
                res += 2
            } else {
                stack.push(target)
            }
        }
    }
    return res
}

fun findPrimesUpTo(n: Int): List<Int> {
    if (n < 2) return emptyList()

    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..n) {
        if (isPrime[i]) {
            for (j in i * 2..n step i) {
                isPrime[j] = false
            }
        }
    }

    return isPrime.indices.filter { isPrime[it] }
}

fun sumOfDigits(s: String): Int {
    return s.filter { it.isDigit() }.sumOf { it.toString().toInt() }
}

fun sortSerialNumbers(serialNumbers: List<String>): List<String> {
    return serialNumbers.sortedWith(compareBy({ it.length }, { sumOfDigits(it) }, { it }))
}

fun mainSort() {
    // 입력을 받는 부분
    val n = readln().toInt()
    val serialNumbers = mutableListOf<String>()
    repeat(n) {
        serialNumbers.add(readln())
    }

    // 정렬된 시리얼 번호를 출력하는 부분
    val sortedSerialNumbers = sortSerialNumbers(serialNumbers)
    sortedSerialNumbers.forEach { println(it) }
}

fun mainChk() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextLine().toInt()
    val arr = mutableListOf<Int>()
    repeat(n) {
        arr.add(scanner.nextInt())
    }
    val res = mutableListOf<Int>()
    for (i in arr.indices) {
        for (j in i + 1 until arr.size) {
            val tmp = arr[i] - arr[j]
            if (tmp < 0) res.add(tmp)
        }
    }
    if (res.isEmpty()) {
        println(0)
        return
    }

    val res2 = res.map { it * -1 }
    println(res2.max())
}

fun main909() {
    fun findMaxPositions(nums: List<Int>): List<Int> {
        val positions = mutableListOf<Int>()
        var endIndex = nums.size

        while (endIndex > 0) {
            var maxIndex = 0
            var maxValue = nums[0]
            for (i in 0 until endIndex) {
                if (nums[i] > maxValue) {
                    maxValue = nums[i]
                    maxIndex = i
                }
            }
            positions.add(maxIndex + 1) // 위치를 1부터 시작하도록 조정
            endIndex = maxIndex
        }

        return positions
    }

    val scanner = Scanner(System.`in`)
    val n = scanner.nextLine().toInt()
    val nums = mutableListOf<Int>()
    repeat(n) {
        nums.add(scanner.nextInt())
    }
    val result = findMaxPositions(nums)
    println(result.joinToString(" "))
}

fun main323() {
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