package com.example.programmerstest

import java.util.Scanner

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

fun main() {
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