package com.example.programmerstest

import java.util.*
import kotlin.collections.ArrayList
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main1(args: Array<String>) = with(Scanner(System.`in`)) {
    val a = nextInt()
    val b = nextInt()

    if (a > 0 && b > 0) println(1)
    if (a < 0 && b > 0) println(2)
    if (a < 0 && b < 0) println(3)
    if (a > 0 && b < 0) println(4)
}

fun main3213() {
    val input = readLine()!!.split(" ").map{it.toInt()}
    val array1 = readLine()!!.split(" ").map{it.toInt()}
    val array2 = readLine()!!.split(" ").map{it.toInt()}

    println(getSymmetricDifference(array1, array2))
}

fun main() = with(Scanner(System.`in`)) {
    var input = nextInt()
    var cnt = 0
    while (true) {
        if (input % 5 == 0) { //5로 나누는게 일단 가장 적은 cnt를 반환할 것이다.
            println(cnt + input / 5)
            return@with
        } else if (input < 0){
            println("-1")
            return@with
        }
        cnt ++
        input -= 3
    }
}

fun getSymmetricDifference(array1: List<Int>, array2: List<Int>): Int {
    val set1 = array1.toSet()
    val set2 = array2.toSet()

    val difference1 = set1.subtract(set2)
    val difference2 = set2.subtract(set1)

    return (difference1 union difference2).size
}

fun mainㄷ23() = with(Scanner(System.`in`)) {
    val first = nextInt()
    val second = nextInt()

    val firstList = mutableListOf<String>()
    val secondList = mutableListOf<String>()

    repeat(first) { firstList.add(next()) }

    repeat(second) { secondList.add(next()) }

    val intersection = firstList.intersect(secondList.toSet()).sorted()
    println(intersection.size)
    intersection.forEach { println(it) }
}

fun mainㅅ654ㅅ() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()

    val arr = IntArray(10001)
    for (i in 0 until n) {
        arr[br.readLine().toInt()]++
    }
    for (i in arr.indices) {
        bw.write("$i\n".repeat(arr[i]))
    }
    br.close()
    bw.close()
}

data class Count(
    val x: Int,
    val y: Int
)

fun mainTest(){
    val num = readLine()!!.toInt()
    val arr = ArrayList<Pair<Int,Int>>()
    for(i in 0 until num){
        val line = readLine()!!.split(" ").map{it.toInt()}
        arr.add(Pair(line[0],line[1]))
    }
    arr.sortWith { d1, d2 ->
        if (d1.second == d2.second) {
            d1.first - d2.first
        } else {
            d1.second - d2.second
        }
    }

    for(i in 0 until num){
        println("${arr[i].first} ${arr[i].second}")
    }
}


fun mainfdsf() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (cntData, cntQuestion) = br.readLine().split(" ").map { it.toInt() }
    val numberKeys = mutableMapOf<Int, String>()
    val nameKeys = mutableMapOf<String, Int>()

    repeat(cntData) {
        val name = br.readLine()
        numberKeys[it + 1] = name
        nameKeys[name] = it + 1
    }

    repeat(cntQuestion) {
        val input = br.readLine()

        if (input[0].isDigit()) {
            bw.write("${numberKeys[input.toInt()]}\n")
        } else {
            bw.write("${nameKeys[input]}\n")
        }
    }
    bw.flush()
    bw.close()
}

fun mainfds() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val map = HashMap<String, Boolean>()

    repeat(n) {
        val line = br.readLine().split(" ")
        map[line[0]] = line[1] == "enter"
    }

    map.keys.sortedDescending().forEach {
        if (map[it]!!) {
            println(it)
        }
    }
}

fun main222222() = with(Scanner(System.`in`)) {
    val (n, m) = readLine()?.split(" ")?.map { it.toInt() } ?: emptyList()
    val hashSet = HashSet<String>()

    repeat(n) {
        readLine()?.let { value -> hashSet.add(value) }
    }

    var cnt = 0
    repeat(m) {
        if (hashSet.contains(readLine())) cnt++
    }



    fun main() = with(Scanner(System.`in`)) {
        val (n, m) = readLine()?.split(" ")?.map { it.toInt() } ?: emptyList()
        val hashSet = HashSet<String>()

        repeat(n) {
            readLine()?.let { value -> hashSet.add(value) }
        }

        var cnt = 0
        repeat(m) {
            if (hashSet.contains(readLine())) cnt++
        }

        println(cnt)
    }

    fun main78() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = readln().toInt()
        val cardSet = readln().split(" ").map { it.toInt() }.toMutableSet()
        val m = readln().toInt()

        readln().split(" ").map { it.toInt() }.forEach {
            if (!cardSet.add(it)) print("1 ") else print("0 ")
        }
    }

    fun main412() = with(System.`in`.bufferedReader()) {
        while (true) {
            val text = readLine()
            if (text == ".") {
                break
            }

            val stack = Stack<Char>()

            var isBalanced = true
            text.forEach {
                if (it == '(' || it == '[') {
                    stack.push(it)
                } else if (it == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') stack.pop() else {
                        isBalanced = false
                        return@forEach
                    }
                } else if (it == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[') stack.pop() else {
                        isBalanced = false
                        return@forEach
                    }
                }
            }
            if (stack.isNotEmpty()) isBalanced = false
            println(
                when (isBalanced) {
                    true -> "yes"
                    false -> "no"
                }
            )
        }
    }

    fun main3213() = with(Scanner(System.`in`)) {
        var n = 0
        var m = 0
        var sum = 0
        var max = 0

        n = nextInt()
        m = nextInt()

        val arr: ArrayList<Int> = arrayListOf()

        for (i in 0 until n) {
            arr.add(nextInt())
        }

        for (one in 0 until n) {
            for (two in one + 1 until n) {
                for (three in two + 1 until n) {
                    sum = arr[one] + arr[two] + arr[three]
                    if (sum in (max + 1)..m) max = sum
                }
            }
        }
        println(max)
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

    fun main4312() {
        with(System.`in`.bufferedReader()) {
            with(System.out.bufferedWriter()) {
                val N = readLine().toInt()
                val arr = readLine().split(" ").map(String::toInt)
                val sortedArr = arr.distinct().sorted()

                //이미 오름차순 정렬이 적용된 배열에 이분 탐색을 활용한다.
                for (i in 0 until N) {
                    write(sortedArr.binarySearch(arr[i]).toString() + " ")
                }
                close()
            }
            close()
        }
    }

    fun main4535() = with(Scanner(System.`in`)) {
        val stack = Stack<Char>()

        repeat(nextInt()) {
            val input = next().toCharArray().toList()
            stack.addAll(input)

            val right = mutableListOf<Char>()
            val left = mutableListOf<Char>()

            while (stack.isNotEmpty()) {
                val pop = stack.pop() ?: break
                if (pop == '(') left.add(pop)
                else right.add(pop)
            }

            if (right.size == left.size) {
                println("YES")
            } else {
                println("NO")
            }
        }
    }

    fun main040() = with(Scanner(System.`in`)) {
        val stack = Stack<Int>()

        fun check(key: Int, value: Int? = null) {
            when (key) {
                1 -> {
                    stack.push(value)
                }
                2 -> {
                    val res = try {
                        stack.pop() ?: -1
                    } catch (e: EmptyStackException) {
                        -1
                    }
                    println(res)
                }
                3 -> {
                    println(stack.size)
                }
                4 -> {
                    println(if (stack.isEmpty()) 1 else 0)
                }
                5 -> {
                    val res = try {
                        stack.peek() ?: -1
                    } catch (e: EmptyStackException) {
                        -1
                    }
                    println(res)
                }
            }
        }

        repeat(nextInt()) {
            val input = nextInt()
            if (input != 1) {
                check(input)
            } else {
                val second = nextInt()
                check(input, second)
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

    fun main22() = with(Scanner(System.`in`)) {
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

    fun main121() = with(Scanner(System.`in`)) {

        fun sums(number: Int): Int {
            var sum = 0
            for (c in number.toString()) sum += c - '0'
            return sum
        }

        val n = nextInt()
        val length = n.toString().length
        for (i in n - 9 * length..n) {
            if (sums(i) + i == n) {
                println(i)
                return@with
            }
        }
        println(0)
    }

    fun main564() = with(Scanner(System.`in`)) {
        val goods = arrayListOf(1, 1, 2, 2, 2, 8)
        repeat(6) {
            goods[it] = goods[it] - nextInt()
        }
        println(goods.joinToString(" "))
    }

    fun main98() = with(Scanner(System.`in`)) {
        val paper = Array(101) { Array(101) { false } }

        repeat(nextInt()) {
            val (p, q) = next().toString().split(" ").map { it.toInt() }

            repeat(10) { x ->
                repeat(10) { y ->
                    paper[x + p][y + q] = true //색종이의 범위에 들어가는 부분은 true (중복 범위는 신경 안써도 됨)
                }
            }
        }

        println(paper.sumOf { it.count { value -> value } }) //true인 부분 모두 카운트 계산
    }

//fun main() = with(System.`in`.bufferedReader()) {
//    val n = readLine().toInt()
//
//    val map = Array(101) { Array(101) { false } }
//
//    repeat(n) {
//        val (p1, p2) = readLine().split(" ").map { it.toInt() }
//
//        repeat(10) { dx ->
//            repeat(10) { dy ->
//                map[p1 + dx][p2 + dy] = true
//            }
//        }
//    }
//
//    println(map.sumOf { it.count { it } })
//}


    fun main1311() = with(Scanner(System.`in`)) {
        val a = nextInt()
        val b = nextInt()
        val c = nextInt()
        val d = nextInt()
        val e = nextInt()
        val f = nextInt()

        for (i in -999 until 1000) {
            for (j in -999 until 1000) {
                if (a * i + b * j == c && d * i + e * j == f) {
                    println("$i $j")
                    break
                }
            }
        }
    }

    fun main2122() = with(Scanner(System.`in`)) {
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

    fun main434() = with(Scanner(System.`in`)) {
//    val having = mutableListOf<Int>()
//    val size = nextInt()
//    repeat(size) {
//        having.add(nextInt())
//    }
//    val check = mutableListOf<Int>()
//    val checkSize = nextInt()
//    repeat(checkSize) {
//        if (having.contains(nextInt())) {
//            check.add(1)
//        } else check.add(0)
//    }
//    println(check.joinToString(" "))

        val n = nextInt()
        val cardSet = next().split("").map { it.toInt() }.toMutableSet()
        val m = nextInt()

        next().split("").map { it.toInt() }.forEach {
            if (!cardSet.add(it)) println("1") else println("0")
        }
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
}

