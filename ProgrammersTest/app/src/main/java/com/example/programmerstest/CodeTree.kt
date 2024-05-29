package com.example.programmerstest

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val arr = scanner.nextLine().split(" ").map { it.toInt() }
    val arrUnder = arr.filter { it < 500 }.sortedDescending()
    val arrOver = arr.filter { it > 500 }.sorted()

    println("${arrUnder.first()} ${arrOver.first()}")
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