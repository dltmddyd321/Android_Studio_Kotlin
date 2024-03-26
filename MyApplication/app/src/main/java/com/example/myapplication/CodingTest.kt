package com.example.myapplication

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.Scanner
import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.sqrt


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

//모든 자연수는 소수들의 곱으로 표현이 된다. 제일 작은 소수 2부터 시작한다.
// 2부터 N-1까지의 수 중에서 2의 배수를 모두 체로 거르고 남은 숫자들 중에서 
// 3의 배수로 거르고를 반복해서 제곱근N 까지 나눠서 걸러지지 않고 남은 수들이 모두 소수가 된다.
//주어진 자연수 N이 소수이기 위한 필요충분 조건은 N이 N의 제곱근보다 크지 않은 어떤 소수로도 나눠지지 않는다.
// 수가 수를 나누면 몫이 발생하게 되는데 몫과 나누는 수, 둘 중 하나는 반드시 N의 제곱근 이하이기 때문이다.
fun main7865() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    for (i in 0 until n) {
        val str = br.readLine()
        val strArr = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        print("Case #" + (i + 1) + ": ")
        for (j in strArr.indices.reversed()) {
            print(strArr[j] + " ")
        }
        println()
    }
}

fun checkStack() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val stack = Stack<Int>()
    val arr = IntArray(readLine().toInt()) { readLine().toInt() }
    var tmp = 1

    for (num in arr) {
        while (tmp < num) {
            stack.push(tmp++)
            sb.append("+\n")
        }
        if (stack.peek() == num) {
            stack.pop()
            sb.append("-\n")
        } else {
            println("NO")
            return@with
        }
    }
    println(sb)
}

fun main() = with(System.out.bufferedWriter()) {
    val br = System.`in`.bufferedReader()
    val set = DoubleArray(200)
    fun cal(l: Double, r: Double, ch: Char): Double = when (ch) {
        '*' -> l * r

        '-' -> l - r

        '+' -> l + r

        else -> l / r
    }

    val n = br.readLine().toInt()
    val input = br.readLine()
    val stk = Stack<Double>()
    for (i in 0 until n) {
        ('A' + i).code
        set[('A' + i).code] = br.readLine().toDouble()
    }

    for (ch in input) {
        if (ch.isLetter()) {
            stk.push(set[ch.code])
        } else {
            val r = stk.pop()
            val l = stk.pop()
            stk.push(cal(l, r, ch))
        }
    }
    write(String.format("%.2f", stk.pop()))
    close()
}


fun maine421() {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    //초기 배열
    val check = BooleanArray(M + 1) { true }

    for (n in N..M) {
        val sqrt = sqrt(n.toDouble()).toInt()
        // 2부터 N의 제곱근까지의 수까지 나눠서 나누지면 반복문 종료
        for (i in 2..sqrt) {
            if (n % i == 0) {
                check[n] = false
                break
            }
        }
    }

    for (i in N..M) {
        if (i == 1) continue
        if (check[i]) println(i)
    }
}

fun main4342() = with(Scanner(System.`in`)) {
    val input = readln()
    var res = ""
    val errorPattern = "__.+".toRegex()
    if ((input.contains('_') && input.any { it.isUpperCase() })
        || (!input.contains('_') && input.all { it.isLowerCase() })
        || input.first() == '_' || input.first().isUpperCase()
        || errorPattern.containsMatchIn(input) || input.last() == '_'
        || input.length > 100 || input.last().isUpperCase()
    ) {
        println("Error!")
    }
    if (input.contains('_')) {
        var needToUpper = false
        input.forEach {
            if (needToUpper) {
                res += it.uppercase()
                needToUpper = false
            } else if (it == '_') {
                needToUpper = true
            } else {
                res += it
            }
        }
    } else if (input.find { it.isUpperCase() } != null) {
        input.forEach {
            if (it.isUpperCase()) {
                res += "_${it.lowercase()}"
            } else {
                res += it
            }
        }
    }
    println(res)
}


fun main65() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    fun GCD(a: Long, b: Long): Long {
        if (a % b == 0L) return b
        return GCD(b, a % b)
    }

    fun LCM(N: Long, M: Long, gcd: Long): Long = (N * M) / gcd

    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toLong()
    val M = st.nextToken().toLong()

    val gcd = GCD(N, M)
    val lcm = LCM(N, M, gcd)
    println(gcd)
    println(lcm)
}


fun main09() {
    fun hasUnderBar(str: String): Boolean = str.contains("_")
    var first = true
    val input = readln()
    var res = ""

    // 첫 번째 글자가 _나 대문자로 시작하는 경우 또는 마지막 글자가 _로 끝나는 경우
    if (input.isEmpty() || !input[0].isLowerCase() || input.last() == '_') {
        println("Error!")
        return
    }

    if (hasUnderBar(input)) {
        val tokens = input.split('_')
        for (word in tokens) {
            if (word.isEmpty()) {
                println("Error!")
                return
            }
            var w = word
            if (w[0].isLowerCase()) {
                if (!first) {
                    w =
                        w.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                } else {
                    first = false
                }
                res += w
            } else {
                //_ 앞에 대문자로 시작한다??
                println("Error!")
                return
            }
            //중간에 대문자가 존재하는지 검사
            for (i in 1 until w.length) {
                if (w[i].isUpperCase()) {
                    println("Error!")
                    return
                }
            }
        }
    } else {
        var tmp = ""
        for (e in input) {
            if (e.isUpperCase()) {
                res += tmp
                res += '_'
                tmp = ""
                tmp += e.lowercase()
            } else {
                tmp += e
            }
        }
        res += tmp
    }
    println(res)
}

fun main232() = with(Scanner(System.`in`)) {
    val n = readln().toInt()
    val pArr = readln().split("\\*".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()
    val left = pArr[0]
    val right = pArr[1]
    val res = mutableListOf<String>()
    repeat(n) {
        val input = readln()
        if (input.length < left.length + right.length) {
            res.add("NE")
        } else {
            val front = input.substring(left.indices)
            val back = input.substring(input.length - right.length, input.length)
            if (front == left && back == right) {
                res.add("DA")
            } else {
                res.add("NE")
            }
        }
    }
    res.forEach {
        println(it)
    }
}

fun main54() {
    val word = readln()
    val map = mutableMapOf<Char, Int>()

    word.forEach {
        val character = it.lowercaseChar()

        if (map.containsKey(character)) {
            map[character] = map[character]!!.plus(1)
        } else {
            map[character] = 1
        }
    }

    val max = map.maxBy { it.value }
    if (map.filter { it.value == max.value }.count() > 1) {
        print("?")
    } else {
        print(max.key.uppercaseChar())
    }
}

fun main4444() = with(Scanner(System.`in`)) {
    val n = readln()
    var res = 0
    readln().map { it.toString().toInt() }.toIntArray().forEach {
        res += it
    }
    println(res)
}

fun main111111() = with(Scanner(System.`in`)) {
    val list = mutableListOf<String>()
    val n = readln().toInt()
    repeat(n) { list.add(readln()) }
    list.forEach { str ->
        val frequencies =
            str.replace(" ", "").map { value -> value.toString() }.groupingBy { char -> char }
                .eachCount()
        val maxFrequency = frequencies.maxBy { it.value }.value
        val mostFrequentElements = frequencies.filter { it.value == maxFrequency }.keys
        val result = if (mostFrequentElements.size > 1) "?" else mostFrequentElements.first()
        println(result)
    }
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
