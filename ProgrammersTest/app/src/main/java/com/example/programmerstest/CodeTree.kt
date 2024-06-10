package com.example.programmerstest

import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.util.Scanner
import java.util.Stack
import kotlin.math.abs

fun main() {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        var arr = mutableListOf<Int>()
        repeat(n) { arr.add(it + 1) }

        var cnt = 0
        while (true) {
            val isNeedToCheck = arr.any { it in section }
            if (!isNeedToCheck) break

            try {
                val startIndex = arr.indexOfFirst { it in section } + m
                arr = arr.subList(startIndex, arr.size - 1)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
                break
            } finally {
                cnt++
            }
        }
        return cnt
    }

    println(solution(8, 4, intArrayOf(2, 3, 6)))
}

fun main6453() {
    val scanner = Scanner(System.`in`)
    val start = scanner.nextInt()
    val end = scanner.nextInt()

    fun isPerfectNumber(n: Int): Boolean {
        var sum = 0
        for (i in 1 until n) {
            if (n % i == 0) {
                sum += i
            }
        }
        return sum == n
    }

    var count = 0
    for (n in start..end) {
        if (isPerfectNumber(n)) {
            count++
        }
    }

    println(count)
}

fun solutionOne(brown: Int, yellow: Int): IntArray {
    fun findPairsForProduct(n: Int): List<Pair<Int, Int>> {
        val pairs = mutableListOf<Pair<Int, Int>>()

        for (i in 1..n) {
            if (n % i == 0) {
                if (i >= 3 && (n / i) >= 3) {
                    pairs.add(Pair(i, n / i))
                }
            }
        }

        return pairs
    }

    val list = findPairsForProduct(brown + yellow)
    val res = list.find { it.first >= it.second && it.first >= 3 } ?: return intArrayOf()

    return intArrayOf(res.first, res.second)
}

fun solutionLotto(lottos: IntArray, win_nums: IntArray): IntArray {
    var min = 0
    var max = 0
    val res = mutableListOf<Int>()
    val winNumbers = win_nums.toMutableList()

    fun check(value: Int): Int {
        return when (value) {
            6 -> 1
            5 -> 2
            4 -> 3
            3 -> 4
            2 -> 5
            else -> 6
        }
    }

    if (lottos.all { it != 0 }) { //0이 하나도 없을 경우, 교집합 개수를 반환
        min = lottos.intersect(winNumbers.toSet()).size
        repeat(2) { res.add(check(min)) }
        return res.toIntArray()
    }

    val intersection = lottos.intersect(winNumbers.toSet())
    min = intersection.size //0이 하나 있으면 무조건 1 더해준다.

    winNumbers.removeIf { it in lottos }
    val diff = winNumbers.size //아직 선택 가능한 정답 개수
    val zeroCnt = lottos.count { it == 0 }

    max = if (zeroCnt > diff) { //0의 공백을 정답으로 채우기 부족할 때,
        intersection.size + diff
    } else { //공백 개수랑 가능한 정답 개수가 동일할 경우 또는 공백 개수가 정답 개수보다 적을 경우
        intersection.size + zeroCnt
    }

    res.add(check(max))
    res.add(check(min))

    return res.toIntArray()
}

fun solutionPhone(numbers: IntArray, hand: String): String {
    val phoneArray = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("*", "0", "#")
    )

    var (leftX, leftY) = Pair(0, 3)
    var (rightX, rightY) = Pair(2, 3)

    val res = StringBuilder()

    val leftNum = phoneArray.map { it[0] }
    val rightNum = phoneArray.map { it[2] }

    numbers.forEach { num ->
        val value = num.toString()

        val y = phoneArray.indexOfFirst { value in it }
        val x = phoneArray[y].indexOfFirst { it == value }

        if (value in leftNum) {
            res.append("L")
            leftX = x
            leftY = y
        } else if (value in rightNum) {
            res.append("R")
            rightX = x
            rightY = y
        } else {
            val leftDiff = abs(leftX - x) + abs(leftY - y)
            val rightDiff = abs(rightX - x) + abs(rightY - y)
            if (leftDiff > rightDiff) {
                res.append("R")
                rightX = x
                rightY = y
            } else if (leftDiff < rightDiff) {
                res.append("L")
                leftX = x
                leftY = y
            } else {
                if (hand == "right") {
                    res.append("R")
                    rightX = x
                    rightY = y
                } else {
                    res.append("L")
                    leftX = x
                    leftY = y
                }
            }
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