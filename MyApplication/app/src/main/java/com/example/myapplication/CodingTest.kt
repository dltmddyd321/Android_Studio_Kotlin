package com.example.myapplication

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

fun main121() = with(Scanner(System.`in`)) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    println(n * k - 1)
}

fun main323() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        readLine().split(" ").map { it.reversed() }.forEach {
            print("$it ")
        }
        println()
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st1 = br.readLine()
    val st2 = br.readLine()
    val arr = Array('z' - 'a' + 1) { 0 }
    var result = 0
    for (s in st1) {
        arr[(s - 'a')] += 1
    }
    for (s in st2) {
        arr[(s - 'a')] -= 1
    }
    for (i in arr) {
        result += if (i < 0) (-1) * i else i
    }
    print(result)
}

fun main4343() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val list = arrayListOf<Int>()
    val stack = Stack<Int>()
    val sb = StringBuilder()

    for (i in 0 until n) {
        val next = st.nextToken().toInt()
        list.add(next)
    }

    for (i in 0 until list.size) {
        // 스택이 비어있지않고, 오큰수를 찾았을 때 pop 하여 index 값을 오큰수로 변경
        while (stack.isNotEmpty() && list[stack.peek()] < list[i]) {
            list[stack.pop()] = list[i] //바라보고 있는 값이 곧 오큰수로 지정되도록 한다.
        }
        stack.push(i) // 인덱스값 push
    }

    // 스택에 남아있는 인덱스는 오큰수를 찾지 못한 것
    while (stack.isNotEmpty()) {
        list[stack.pop()] = -1
    }

    for (i in 0 until list.size) {
        sb.append("${list[i]} ")
    }

    println(sb)
}

fun main5443() {
    val list: LinkedList<Int> = LinkedList()

    val n = readln().toInt()

    repeat(n) {
        when (val line = readln()) {
            "pop_front" -> if (list.size == 0) println(-1) else println(list.removeFirst())

            "pop_back" -> if (list.size == 0) println(-1) else println(list.removeLast())

            "size" -> println(list.size)

            "empty" -> println(if (list.size == 0) 1 else 0)

            "front" -> if (list.size == 0) println(-1) else println(list.first)

            "back" -> if (list.size == 0) println(-1) else println(list.last)

            else -> {
                if (line.startsWith("push_front")) {
                    list.addFirst(line.split(" ")[1].toInt())
                } else {
                    list.addLast(line.split(" ")[1].toInt())
                }
            }
        }
    }
}

fun main67() {
    val stringBuilder = StringBuilder()

    val (number, sequenceNumber) = readln().split(" ").map { it.toInt() }
    val numberQueue = LinkedList(Array(number) { it + 1 }.toList())

    var index: Int = -1
    while (numberQueue.isNotEmpty()) {
        index = (index + sequenceNumber) % numberQueue.size
        stringBuilder.append(numberQueue.removeAt(index--)).append(", ")
    }

    with(stringBuilder) {
        // 맨 끝의 콤마, 공백을 삭제함
        this.deleteCharAt(this.length - 1)
        this.deleteCharAt(this.length - 1)
        this.insert(0, '<')
        this.insert(this.length, '>')
    }

    println(stringBuilder.toString())
}

fun main445() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val leftStack = readLine().map { it.toString() }.toMutableList()
    val rightStack = mutableListOf<String>() // 리스트로 스택 구현
    val n = readLine().toInt()

    repeat(n) {
        when (val cmd = readLine()) {
            "L" -> {
                if (leftStack.isNotEmpty()) {
                    rightStack.add(leftStack.removeAt(leftStack.lastIndex))
                }
            }

            "D" -> {
                if (rightStack.isNotEmpty()) {
                    leftStack.add(rightStack.removeAt(rightStack.lastIndex))
                }
            }

            "B" -> {
                if (leftStack.isNotEmpty()) {
                    leftStack.removeAt(leftStack.lastIndex)
                }
            }

            else -> {
                leftStack.add(cmd[2].toString())
            }
        }
    }

    val answer = StringBuilder()
    leftStack.forEach { answer.append(it) }
    for (i in rightStack.lastIndex downTo 0) { // 오른쪽 스택은 top부터 문자를 읽어야 한다
        answer.append(rightStack[i])
    }
    print(answer)
}

fun main45() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    val q: Queue<Int> = LinkedList()
    while (n-- > 0) {
        val ip = readLine().split(' ')
        when (ip[0]) {
            "push" -> q.add(ip[1].toInt())
            "pop" -> println(if (q.isEmpty()) -1 else q.poll())
            "empty" -> println(if (q.isEmpty()) 1 else 0)
            "front" -> println(if (q.isEmpty()) -1 else q.peek())
            "back" -> println(if (q.isEmpty()) -1 else q.last())
            else -> println(q.size)
        }
    }
}

fun main222() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val stack = Stack<Int>()
    val arr = IntArray(readLine().toInt()) { readLine().toInt() }
    var cur = 1

    for (num in arr) {
        // 추가해야 하는 수가 수열의 현재 수 이하면 스택에 계속 추가
        while (cur <= num) {
            stack.push(cur++)
            sb.append("+\n")
        }
        // 스택의 가장 위 값이 수열의 현재 수와 같으면 스택에서 제거
        if (stack.peek() == num) {
            stack.pop()
            sb.append("-\n")
        }
        // 스택의 가장 위 값이 수열의 현재 수와 다르면(크면) 더 작은 수를 추가할 수 없으므로 프로그램 종료
        else {
            println("NO")
            return@with
        }
    }
    println(sb)
}

fun main1() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val queue: Queue<Int> = LinkedList()
    val sb = StringBuffer("<")

    repeat(n) { queue.add(it + 1) }

    while (queue.isNotEmpty()) {
        for (i in 0 until k - 1) {
            //맨 앞의 원소를 맨 뒤로 보낸다.
            queue.offer(queue.poll())
        }

        //K번째 원소는 빼거나 마지막 하나 남았을 경우 잔여 처리한다.
        if (queue.size == 1) sb.append("${queue.poll()}")
        else sb.append("${queue.poll()}, ")
    }
    println("$sb>")
}

fun main33() = with(System.`in`.bufferedReader()) {
    val n = read()
    val list = mutableListOf<Int>()
    repeat(n) { list.add(read()) }

    val roundedAverage = String.format("%.1f", list.average())
    println(roundedAverage)

    val sortedList = list.sorted()
    println(sortedList[sortedList.size / 2])

    val modeMap = list.groupingBy { it }.eachCount() // 각 값의 개수를 세는 맵 생성
    val maxCount = modeMap.values.maxOrNull() // 최빈값의 개수
    val modes = modeMap.filterValues { it == maxCount }.keys // 최빈값들의 리스트
    println(modes.first())

    val diff = list.max() - list.min()
    println(diff)
}

private data class Student(var name: String, var kor: Int, var eng: Int, var math: Int)

fun main5345() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()

    val students = Array(n) { Student("", 0, 0, 0) }

    repeat(n) {
        val st = StringTokenizer(br.readLine())
        students[it].name = st.nextToken()
        students[it].kor = st.nextToken().toInt()
        students[it].eng = st.nextToken().toInt()
        students[it].math = st.nextToken().toInt()
    }

    // 디폴트가 오름차순
    students.sortWith(compareBy({ -it.kor }, // -를 붙이면 내림차순
        { it.eng }, { -it.math }, { it.name })
    )

    students.forEach { println(it.name) }
}

fun maindddd() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val list = mutableListOf<Long>()
    repeat(n) { list.add(readln().toLong()) }

    val frequencyMap = list.groupingBy { it }.eachCount()
    val mostFrequentEntries =
        frequencyMap.filter { it.value == frequencyMap.values.maxOrNull() }.keys.toList()
    println(mostFrequentEntries.max())
}

fun main32() {

    //    val n = readln().toInt()
    //    val list = mutableListOf<Long>()
    //    repeat(n) { list.add(readln().toLong()) }
    //
    //    val frequencyMap = list.groupingBy { it }.eachCount()
    //    val mostFrequentEntries = frequencyMap.filter { it.value == frequencyMap.values.maxOrNull() }.keys.toList()
    //    println(mostFrequentEntries.max())

    val br = BufferedReader(InputStreamReader(System.`in`))
    val num = br.readLine().toInt()
    val map: MutableMap<Long, Int> = HashMap()
    var max = 1
    var input: Long
    for (i in 0 until num) {
        input = br.readLine().toLong()
        if (map.containsKey(input)) {
            val `val` = map[input]!!
            if (`val` + 1 > max) {
                max = `val` + 1
            }
            map[input] = `val` + 1
        } else {
            map[input] = 1
        }
    }
    val list: MutableList<Long> = ArrayList()
    for ((key, value) in map) {
        if (value == max) list.add(key)
    }
    list.sort()
    println(list[0])
}

fun mainr5() = with(Scanner(System.`in`)) {
    val list = mutableListOf<Int>()
    repeat(9) { list.add(nextInt()) }

    val selected = mutableListOf<Int>()
    val sum = list.sum()
    for (i in 0 until list.size) {
        for (j in i + 1 until list.size) {
            if (sum - (list[i] + list[j]) == 100) {
                list.forEach {
                    if (it != list[i] && list[j] != it) {
                        selected.add(it)
                    }
                }
                selected.sorted().forEach {
                    println(it)
                }
                return@with
            }
        }
    }
}
