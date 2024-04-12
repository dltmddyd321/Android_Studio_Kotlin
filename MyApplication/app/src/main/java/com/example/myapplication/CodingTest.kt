package com.example.myapplication

import java.io.*
import java.util.Arrays
import java.util.Deque
import java.util.LinkedList
import java.util.Queue
import java.util.Scanner
import java.util.Stack
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.*


fun stt(s: String): Int {
    val (h, m) = s.split(":").map { it.toInt() }
    return h * 100 + m
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val num = br.readLine().toInt()
    val mode = br.readLine().split(' ').map { it.toInt() }
    val queueStack = br.readLine().split(' ').map { it.toInt() }
    var m = br.readLine().toInt()
    val insert = br.readLine().split(' ').map { it.toInt() }

    val sb = StringBuilder()
    for (i in num - 1 downTo 0) { // 마지막 큐부터 출력 -> 큐스택
        if (m == 0) break // 삽입된만큼 출력하면 종료
        if (mode[i] == 0) { // 큐
            sb.append("${queueStack[i]} ")
            m--
        }
    }
    for (i in insert.indices) { // 삽입된 원소 출력
        if (m == 0) break
        sb.append("${insert[i]} ")
        m--
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}


fun main45345() {
    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val coins = Array(n) { br.readLine().toInt() }
    var res = 0
    var tmp = k

    for (index in n - 1 downTo 0) {
        res += tmp / coins[index]
        tmp %= coins[index]
    }

    println(res)
}

fun maint43() {
    val scanner = Scanner(System.`in`)
    val s = scanner.next()
    val e = scanner.next()
    val q = scanner.next()

    val sTime = stt(s)
    val eTime = stt(e)
    val qTime = stt(q)

    val us = mutableSetOf<String>()
    val uscnt = mutableSetOf<String>()

    while (scanner.hasNext()) {
        val s1 = scanner.next()
        if (s1 == "") break
        val s2 = scanner.next()
        if (s2 == "") break

        val s1Time = stt(s1)

        when {
            s1Time <= sTime -> us.add(s2)
            s1Time in eTime..qTime -> if (s2 in us) uscnt.add(s2)
        }
    }
    println(uscnt.size)
}

fun main876() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var temp = reader.readLine()?.split(" ".toRegex())?.dropLastWhile { it.isEmpty() }
        ?.toTypedArray() ?: return
    val s = temp[0]
    val e = temp[1]
    val q = temp[2]
    val input = reader.readLine() ?: return
    val chance = HashSet<String>()
    val result = HashSet<String>()
    var cnt = 0
    while (true) {
        temp = input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (temp.isEmpty()) break
        if (s >= temp[0]) {
            chance.add(temp[1])
        } else if (temp[0] in e..q) {
            result.add(temp[1])
        }
    }
    for (str in chance) {
        if (result.contains(str)) {
            cnt++
        }
    }
    println(cnt)
}

/*
TreeMap
우선 트라이를 쓰든 해쉬만 쓰든 정렬은 해야 한다.
따라서 HashMap이 아닌 TreeMap을 써주자.
트리맵은 자동 정렬되니 이후 트리맵을 순회하면서 출력하면 끝
 */
val br = System.`in`.bufferedReader()
fun mainTree() = with(System.out.bufferedWriter()) {
    val map = TreeMap<String, Double>()
    var size = 0
    while (true) {
        val input = br.readLine() ?: break
        map[input] = map.getOrDefault(input, 0.0) + 1
        size++
    }
    map.forEach {
        write("${it.key} ${String.format("%.4f", it.value / size * 100)}\n")
    }
    close()
}

fun main654() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val arr = IntArray(n)
    var cnt = 0
    val ingredient = br.readLine().split(" ").toList()
    for (i in ingredient.indices) {
        arr[i] = (ingredient[i].toInt())
    }
    for (i in arr.indices) {
        for (j in i + 1 until arr.size) {
            if (arr[i] + arr[j] == m) {
                cnt++
                break
            }
        }
    }
    print(cnt)
}

fun main65467() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var tc = readLine().toInt()
    while (tc-- > 0) {
        val input = readLine().toInt()
        val mp = HashMap<String, Int>()
        for (i in 0 until input) {
            val a = readLine().split(" ")[1]
            if (mp.containsKey(a)) mp[a] = 1 + mp[a]!!
            else mp[a] = 1
        }
        var ans = 1
        for (a in mp.values) ans *= (a + 1)
        println(ans - 1)
    }
}

fun main534554() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val N = br.readLine().toInt()
    val arr = IntArray(N)
    val sorted_arr = IntArray(N)
    val deque: Deque<Int> = LinkedList()
    val st = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        arr[i] = st.nextToken().toInt()
        sorted_arr[i] = arr[i]
    }
    val K = br.readLine().toInt()
    var sort_max = 0 // 어디까지 정렬할 건지
    for (i in 0 until K) {
        val str = br.readLine()
        val A = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].toInt()
        val B = str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toInt()
        sort_max = max(sort_max.toDouble(), max(A.toDouble(), B.toDouble())).toInt()
        while (!deque.isEmpty() && abs(deque.last.toDouble()) <= A) {
            deque.pollLast()
        }
        deque.addLast(A)
        while (!deque.isEmpty() && abs(deque.last.toDouble()) <= B) {
            deque.pollLast()
        }
        deque.addLast(-B)
    }
    deque.addLast(0) // 마지막 연산 위해
    Arrays.sort(arr, 0, sort_max) // arr 정렬하기
    var final_idx = sort_max - 1
    var ascend_idx = sort_max - 1
    var descend_idx = 0
    var cur = deque.pollFirst()
    while (!deque.isEmpty()) {
        val next = deque.pollFirst()
        val diff = (abs(cur.toDouble()) - abs(next.toDouble())).toInt()
        if (cur > 0) {  // 오름차순
            for (i in 0 until diff) {
                sorted_arr[final_idx--] = arr[ascend_idx--]
            }
        } else {    // 내림차순
            for (i in 0 until diff) {
                sorted_arr[final_idx--] = arr[descend_idx++]
            }
        }
        cur = next
    }
    for (i in sorted_arr.indices) {
        bw.write(sorted_arr[i].toString() + " ")
    }
    br.close()
    bw.flush()
    bw.close()
}

fun main_error_13415() {
    val br = System.`in`.bufferedReader()
    val size = br.readLine().toInt()
    val list = Arrays.stream(br.readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()).mapToInt { s: String -> s.toInt() }.toArray()

    val cnt = br.readLine().toInt()
    var res = list.toList()
    repeat(cnt) {
        val ab = Arrays.stream(br.readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()).mapToInt { s: String -> s.toInt() }.toArray()
        val a = ab[0]
        val b = ab[1]
        val aList = res.slice(0..<a).sorted() + res.slice(a..<res.size)
        res = aList.slice(0..<b).sortedDescending() + aList.slice(b..<aList.size)
    }
    println(res.joinToString(" "))
}

fun main90() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine()!!.split(" ").map { it.toInt() }
    val choice = br.readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val q = LinkedList<Int>()
    for (i in 1..n) q.add(i)
    var res = 0
    var count = 0
    while (count != m) {
        if (q.first == choice[count]) {
            q.poll()
        } else {
            for (i in 1 until q.size) {
                if (q[q.size - i] == choice[count]) {
                    while (q[0] != choice[count]) {
                        q.add(0, q.last)
                        q.removeLast()
                        res++
                    }
                    q.poll()
                    break
                } else if (q[i] == choice[count]) {
                    while (q[0] != choice[count]) {
                        q.add(q.first)
                        q.removeFirst()
                        res++
                    }
                    q.poll()
                    break
                }
            }
        }
        count++
    }
    println(res)
}

//에라토스테네스의 체
fun main233() = with(Scanner(System.`in`)) {

    val n = nextInt()
    val k = nextInt()
    var cnt = 0

    val list = mutableListOf<Boolean>()
    repeat(n + 1) { list.add(false) }

    for (i in 2..n) {
        for (j in i..n step i) {
            if (!list[j]) {
                cnt++
                list[j] = true
            }
            if (cnt == k) {
                println(j)
                return@with
            }
        }
    }
}

fun main09856() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = java.lang.StringBuilder()
    val result = ArrayList<String>()
    val pattern: Pattern = Pattern.compile("\\d+")
    val M = br.readLine().toInt()
    for (i in 0 until M) {
        val input = br.readLine()
        val matcher: Matcher = pattern.matcher(input)
        while (matcher.find()) {
            val num = matcher.group().replace("^0+".toRegex(), "")
            result.add(num.ifEmpty { "0" })
        }
    }
    result.sortWith { o1: String, o2: String ->
        if (o1.length == o2.length) o1.compareTo(
            o2
        ) else o1.length - o2.length
    }
    for (s in result) sb.append(s).append('\n')
    println(sb)
    br.close()
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

fun main8675() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val leftStack = readLine().map { it.toString() }.toMutableList()
    val rightStack = mutableListOf<String>() // 리스트로 스택 구현
    val n = readLine().toInt()

    repeat(n) {
        when (val cmd = readLine()) {
            "L" -> {
                if (leftStack.isNotEmpty()) {
                    //커서를 기준으로 스택을 분리한다.
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

            else -> leftStack.add(cmd[2].toString())
        }
    }
    val answer = StringBuilder()
    leftStack.forEach { answer.append(it) }
    for (i in rightStack.lastIndex downTo 0) { // 오른쪽 스택은 top부터 문자를 읽어야 한다
        answer.append(rightStack[i])
    }
    print(answer)
}

fun main534() = with(Scanner(System.`in`)) {
    val list = mutableListOf<Int>()
    var maxLine = 0
    var max = 0

    for (i in 0 until 9) {
        val input = nextInt()
        list.add(input)

        if (max < list[i]) {
            max = list[i]
            maxLine = i
        }
    }
    println("$max\n${maxLine + 1}")
}

fun main432() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nm = Arrays.stream(br.readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()).mapToInt { s: String -> s.toInt() }.toArray()
    val n = nm[0]
    val k = nm[1]

    val wheel = CharArray(n) // 바퀴

    Arrays.fill(wheel, '?')
    var arrow = 0 // 화살표

    val discover = BooleanArray('Z'.code - 'A'.code + 1)
    var possible = true

    for (i in 0 until k) {
        val st = StringTokenizer(br.readLine())
        val rot = st.nextToken().toInt()
        val alpha = st.nextToken()[0]
        arrow = (arrow + rot) % n
        if (wheel[arrow] != '?' && wheel[arrow] != alpha) {
            possible = false
            break
        }
        wheel[arrow] = alpha
        discover[alpha.code - 'A'.code] = true
    }

    if (possible) {
        val answer = StringBuilder()
        var t: Int
        for (i in 0 until n) {
            t = arrow - i
            if (t < 0) t += n
            answer.append(wheel[t])
        }
        print(answer)
    } else {
        print('!')
    }
}

fun main78() = with(Scanner(System.`in`)) {
    val n = readln().toInt()
    val stack = Stack<Char>()
    repeat(n) {
        readln().reversed().forEach { stack.add(it) }
        val res = mutableListOf<Char>()
        var cur = 0
        while (stack.isNotEmpty()) {
            val tmp = stack.pop()
            if (tmp == '<') {
                if (cur > 0) cur--
            } else if (tmp == '>') {
                cur++
            } else if (tmp == '-') {
                res.removeLast()
                cur--
            } else { //일반 문자
                res.add(cur + 1, tmp)
                cur++
            }
        }
        println(res.joinToString())
    }
}

fun main15() = with(System.`in`.bufferedReader()) {
    val case = readLine().toInt()
    val answer = StringBuilder()

    for (i in 1..case) {
        val input = readLine()
        val pw = LinkedList<Char>()
        var cursor = 0

        for (c in input) {
            when (c) {
                '<' -> cursor = if (cursor > 0) cursor - 1 else 0
                '>' -> cursor = if (cursor < pw.size) cursor + 1 else pw.size
                '-' -> {
                    if (pw.isNotEmpty() && cursor > 0) pw.removeAt(--cursor)
                }

                else -> {
                    pw.add(cursor++, c)
                }
            }
        }
        for (c in pw) {
            answer.append(c)
        }
        answer.append("\n")
    }
    print(answer.toString())
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

private val queue: Queue<Int> = LinkedList()

fun enqueue(element: Int) {
    queue.offer(element)
}

fun dequeue() {
    queue.poll()
}

fun peek(): Int? {
    return queue.peek()
}

fun isEmpty(): Boolean {
    return queue.isEmpty()
}


class Stack<T> {
    private val arr = mutableListOf<T>()

    fun enqueue(element: T) {
        arr.add(element)
    }

    fun popClassic(): T? {
        if (isEmpty()) {
            return null
        }
        return arr.removeAt(arr.size - 1)
    }

    fun peekClassic(): T? {
        if (isEmpty()) {
            return null
        }
        return arr.last()
    }

    fun isEmptyClassic(): Boolean {
        return arr.isEmpty()
    }

    val stack = Stack<Int>()

    fun enqueue(value: Int) {
        stack.push(value)
    }

    fun dequeue(): Int? {
        return stack.pop()
    }

    fun peek(): Int? {
        return stack.peek()
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}

class Node<T>(val value: T, var next: Node<T>? = null)

class LinkedList<T> {
    private var head: Node<T>? = null

    fun append(value: T) {
        if (head != null) {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = Node(value)
        } else {
            head = Node(value)
        }
    }

    fun prepend(value: T) {
        val newNode = Node(value, head)
        head = newNode
    }

    fun delete(value: T) {
        if (head == null) return

        if (head?.value == value) {
            head = head?.next
            return
        }

        var current = head
        while (current?.next != null) {
            if (current.next?.value == value) {
                current.next = current.next?.next
                return
            }
            current = current.next
        }
    }

    fun deleteFirst() {
        if (head != null) {
            head = head?.next
        }
    }

    fun deleteLast() {
        if (head == null) return

        if (head?.next == null) {
            head = null
            return
        }

        var current = head
        while (current?.next?.next != null) {
            current = current.next
        }
        current?.next = null
    }

    fun display() {
        var currentNode = head
        while (currentNode != null) {
            print("${currentNode.value} -> ")
            currentNode = currentNode.next
        }
        println("None")
    }
}

fun main563() = with(System.out.bufferedWriter()) {
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
    if ((input.contains('_') && input.any { it.isUpperCase() }) || (!input.contains('_') && input.all { it.isLowerCase() }) || input.first() == '_' || input.first()
            .isUpperCase() || errorPattern.containsMatchIn(input) || input.last() == '_' || input.length > 100 || input.last()
            .isUpperCase()
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
    val pArr = readln().split("\\*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
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
