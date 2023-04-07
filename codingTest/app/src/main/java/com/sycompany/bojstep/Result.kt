@file:Suppress("BlockingMethodInNonBlockingContext")

package com.sycompany.bojstep

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayDeque
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() {
    println(weirdArray(intArrayOf(1, 2, 3, 4, 5, 6), 4))
}

//3003번
fun chess() {
    val scanner = Scanner(System.`in`)

    val white = arrayOf(1, 1, 2, 2, 2, 8)
    val input = arrayOfNulls<Int>(6)

    for (i in input.indices) {
        input[i] = scanner.nextInt()
    }

    for (i in white.indices) {
        input[i] = white[i] - input[i]!!
        println(input[i])
    }
}

//14681
fun quadrant(x: Int, y: Int): Int {
    return if (x > 0 && y > 0) {
        1
    } else if (x < 0 && y > 0) {
        2
    } else if (x < 0 && y < 0) {
        3
    } else 4
}

//2525
fun ovenClock() = with(Scanner(System.`in`)) {
    var (h, m) = Pair(nextInt(), nextInt())
    val cookingTime = nextInt()

    if (m + cookingTime >= 60) {
        val plusHour = (m + cookingTime) / 60
        m = (m + cookingTime) % 60
        if (h + plusHour >= 24) {
            h = (h + plusHour) % 24
        } else {
            h += plusHour
        }
    } else {
        m += cookingTime
    }

    println("$h $m")
}

//2480
fun diceResult() = with(Scanner(System.`in`)) {
    val a = nextInt()
    val b = nextInt()
    val c = nextInt()

    val values = listOf(a, b, c)

    if (a == b && b == c) {
        println("${10000 + (a * 1000)}")
    } else if (a == b && b != c) {
        println("${1000 + (a * 100)}")
    } else if (a != b && b == c) {
        println("${1000 + (c * 100)}")
    } else if (a == c && b != c) {
        println("${1000 + (c * 100)}")
    } else if (a != b && b != c && a != c) {
        //배열에서 최대 값을 추출한다.
        println("${Collections.max(values) * 100}")
    }
}

//8393
fun allPlus() = with(Scanner(System.`in`)) {
    val num = nextInt()
    var result = 0
    for (i in 1..num) {
        result += i
    }
    println(result)
}

//영수증
fun receipt() = with(Scanner(System.`in`)) {
    val allPrice = nextInt()
    val typeCnt = nextInt()
    var checkPrice = 0

    for (i in 1..typeCnt) {
        checkPrice += (nextInt() * nextInt())
    }

    if (checkPrice == allPrice) {
        println("Yes")
    } else {
        println("No")
    }
}

//15552 -> 시간 초과 대응 처리
/*
BufferedReader & BufferedWriter : 입력된 데이터가 바로 전달되지 않고 버퍼를 거친 후 전달되므로 Scanner보다 속도가 삐름
readLine : 입력받은 값을 String으로 변환 (여기서는 숫자를 사용하므로 int로 변환)
StringTokenizer : 공백을 기준으로 String을 나눠줌
 */
fun fastAPlusB() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val w = BufferedWriter(OutputStreamWriter(System.out))

    for (i in 1..readLine().toInt()) {
        StringTokenizer(readLine()).run {
            val a = nextToken().toInt()
            val b = nextToken().toInt()

            w.write("Case #$i: ${a + b}\n")
        }
    }
    w.flush()
    w.close()
    close()
}

fun plusCycle() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val w = BufferedWriter(OutputStreamWriter(System.out))
    var number = readLine().toInt()
    val copy = number
    var cnt = 0   // 싸이클 길이 카운트

    do {
        number = number % 10 * 10 + (number / 10 + number % 10) % 10
        cnt++
    } while (copy != number)

    w.write(cnt.toString())
    w.flush()
    w.close()
    close()
}

//4344
fun average() = with(Scanner(System.`in`)) {
    val c = nextInt()

    for (i in 0 until c) {
        var sum = 0
        var avg = 0.0
        var cnt = 0
        val size = nextInt()
        val array = IntArray(size)

        for (j in array.indices) {
            array[j] = nextInt()
        }
        for (j in array.indices) {
            sum += array[j]
        }
        avg = sum.toDouble() / size
        for (j in array.indices) {
            if (array[j] > avg) {
                cnt++
            }
        }
        System.out.printf("%.3f%s\n", cnt.toDouble() / size * 100, "%")
    }
}

//4673
fun selfNumber() {
    val max = 10000
    val arr = IntArray(max)
    var ori = 1

    fun d(n: Int) {
        var num = n
        var sum = n

        while (num > 0) {
            sum += num % 10
            num /= 10
        }

        if (sum >= max) {
            return
        } else {
            arr[sum]++
            d(sum)
        }
    }

    while (ori < max) {
        d(ori++)
    }

    for (i in arr.indices) {
        if (i != 0 && arr[i] == 0) {
            println(i)
        }
    }
}

//그룹 단어 체커
fun groupWord() {
    val n = readln().toInt()
    var groupWords = n

    //단어 개수만큼 반복
    repeat(n) {
        val word = readln()
        val charSet = mutableSetOf<Char>()
        var lastChar: Char? = null
        var isGroup = true

        word.forEach {
            if (lastChar != it) {
                lastChar = it
                //이미 포함되어 있다면 그룹 단어가 아니다.
                if (!charSet.add(it)) isGroup = false
            }
        }
        if (!isGroup) groupWords--
    }
    println(groupWords)
}

//아스키코드
fun dial() {
    val arr =
        intArrayOf(3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 10)

    val br = BufferedReader(InputStreamReader(System.`in`))
    var res = 0
    val str = br.readLine().toCharArray()

    fun checkNum(num: Char): Int = arr[num.toInt() - 65]

    for (ch in str) {
        res += checkNum(ch)
    }

    println(res)
}

//손익분기점
fun test() {
    val s = Scanner(System.`in`)
    val a = s.nextInt()
    val b = s.nextInt()
    val c = s.nextInt()

    val n: Int = if (c - b > 0) {
        a / (c - b) + 1
    } else {
        -1
    }

    println(n)
}

//소수 찾기
fun findNum() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    var checkCnt = 0
    var totalCnt = 0

    for (i in 0 until n) {
        val a = sc.nextInt()

        for (j in 2..a) {
            //소수 조건
            if (a % j == 0) {
                checkCnt++
            }
        }
        if (checkCnt == 1) totalCnt++
        checkCnt = 0
    }
    println(totalCnt)
}

fun rangeNum() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val m = readLine().toInt()
    val n = readLine().toInt()
    var min = 0
    var sum = 0

    val arr = Array(n + 1) { true }

    //0과 1은 소수가 아니다.
    arr[0] = false
    arr[1] = false

    for (i in 2..n) {
        if (arr[i]) {
            //소수의 배수는 전부 소수가 아니다.
            for (j in (2 * i)..n step i) {
                arr[j] = false
            }
        }
    }

    for (i in m..n) {
        if (arr[i]) {
            if (min == 0) {
                min = i
            }
            sum += i
        }
    }

    if (min == 0) {
        println(-1)
    } else {
        println(sum)
        println(min)
    }
}

fun findFountain() = with(Scanner(System.`in`)) {
    val x = nextInt()
    var sum = 1 //분자와 분모의 합
    var tmp = 0

    //분자 분모 합 구하기
    while (tmp < x) {
        tmp += sum
        sum++
    }

    //합이 짝수라면
    if (sum % 2 == 0) {
        val m = (tmp - x + 1)
        val d = sum - m
        println("${m / d}")
    }

    //합이 홀수라면
    else {
        val d = (tmp - x + 1)
        val m = sum - d
        println("${m / d}")
    }
}

//수 정렬하기
fun sortOne() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val list = mutableListOf<Int>()

    repeat(n) {
        list.add(nextInt())
    }

    list.sorted().forEach { println(it) }
}

//피보나치 수열
fun fibo() = with(BufferedReader(InputStreamReader(System.`in`))) {
    fun fibonacci(N: Int): Int {
        return if (N < 2) N else fibonacci(N - 1) + fibonacci(N - 2)
    }

    val n = readLine().toInt()
    println(fibonacci(n))
}

fun dungchi() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val cnt = readln().toInt()
    val inputs = Array(cnt) {
        readln().split(' ').map { it.toInt() }
    }
    val res = StringBuilder()
    for (first in inputs) {
        var rank = 1
        for (second in inputs) {
            if (second[0] > first[0] && second[1] > first[1]) rank++
        }
        res.append(rank).append(' ')
    }
    println(res)
}

fun cutLine() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readln().toInt()
    val p = readln().toInt()
    val arr = ArrayList<Int>()

    for (i in 0 until n) {
        arr.add(readln().toInt())
    }

    arr.sortedDescending()
    println(arr[p - 1])
}

fun sorted() = with(Scanner(System.`in`)) {
    val str = next()
    val arr = arrayOfNulls<String>(str.length)

    for (i in arr.indices) {
        arr[i] = str[i].toString()
    }
    Arrays.sort(arr, Collections.reverseOrder())
    for (i in 1 until arr.size) {
        arr[0] += arr[1]
    }
    println(arr[0])
}

fun numberCard() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readln().toInt()
    val cardSet = readln().split(" ").map { it.toInt() }.toMutableSet()
    val m = readln().toInt()

    readln().split(" ").map { it.toInt() }.forEach {
        if (!cardSet.add(it)) print("1")
        else print("0")
    }
}

fun counter() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val arr = IntArray(n)
    var cnt = 0

    //배열에 입력 값만큼 ADD
    for (i in 0 until n) {
        arr[i] = nextInt()
    }
    val v = nextInt()

    for (i in arr.indices) {
        if (v == arr[i]) cnt++
    }

    println(cnt)
}

fun ceoValue() = with(Scanner(System.`in`)) {
    val arr = IntArray(5)
    var hap = 0

    for (i in arr.indices) {
        arr[i] = nextInt()
        hap += arr[i]
    }

    println(hap / arr.size)
    println(arr[2])
}

fun checkMission() = with(Scanner(System.`in`)) {
    val student = IntArray(31)

    for (i in 1..28) {
        val success = nextInt()
        student[success] = 1
    }
    for (i in 1 until student.size) {
        if (student[i] != 1) println(i)
    }
}

fun croatiaAlphabetSearch() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val croatiaAlphabet = arrayOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")
    var str = readLine()

    croatiaAlphabet.forEach {
        str = str.replace(it, "A")
    }
    println(str.length)
}

fun statistics() {
    fun getAvg(numList: MutableList<Int>): Int = numList.average().roundToInt()

    fun getMid(numList: MutableList<Int>): Int {
        val sortedList = numList.sorted()
        val mid = numList.size / 2
        return sortedList[mid]
    }

    fun getFreq(numList: MutableList<Int>): Int {
        val freqMap = numList.groupingBy { it }.eachCount()
        val maxValue = freqMap.maxOf { it.value }
        val maxFreqMap = freqMap.filter { it.value == maxValue }
        val mapKeys = maxFreqMap.keys.sorted()

        return if (mapKeys.size == 1) {
            mapKeys.first()
        } else {
            mapKeys[1]
        }
    }

    fun getRange(numList: MutableList<Int>): Int {
        val maxValue = numList.maxOf { it }
        val minValue = numList.minOf { it }
        return maxValue - minValue
    }
}

fun zeroStack() = with(System.`in`.bufferedReader()) {
    val k = readLine().toInt()
    val stack = arrayListOf<Int>()

    for (i in 0 until k) {
        val temp = readLine().toInt()
        if (temp == 0) stack.removeAt(stack.size - 1)
        else stack.add(temp)
    }
    println(stack.sum())
}

fun setPointUp() = with(Scanner(System.`in`)) {
    data class Point(
        val x: Int, val y: Int
    )

    val list = arrayListOf<Point>()
    val cnt = nextInt()
    repeat(cnt) {
        list.add(Point(nextInt(), nextInt()))
    }

    val result = list.sortedWith(compareBy({ it.x }, { it.y }))

    println(result)
}

fun yaksu() = with(Scanner(System.`in`)) {
    val list = arrayListOf<Int>()
    val cnt = nextInt()
    repeat(cnt) {
        list.add(nextInt())
    }
    val result = list.max() * list.min()
    println(result)
}

fun stringSet() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val m = nextInt()
    val setList = arrayListOf<String>()
    val inputList = arrayListOf<String>()

    repeat(n) {
        setList.add(nextLine())
    }
    repeat(m) {
        inputList.add(nextLine())
    }

    val result = setList.intersect(inputList).size
    println(result)
}

fun stringSetTwo() = with(System.out.bufferedWriter()) {
    val br = System.`in`.bufferedReader()
    val set = HashSet<String>()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    var answer = 0
    for (i in 0 until n) {
        set.add(br.readLine())
    }
    for (i in 0 until m) {
        if (set.contains(br.readLine())) answer++
    }

    write("$answer")
    close()
}

//듣보잡
fun noSeeNoHeard() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val neverHeard = HashMap<String, Boolean>()
    val neverSeen = HashMap<String, Boolean>()
    val never = mutableListOf<String>()

    val cnt = br.readLine().split("")

    repeat(cnt[0].toInt() + cnt[1].toInt()) {
        val man = br.readLine()

        if (it < cnt[0].toInt()) {
            neverHeard[man] = true
        } else {
            neverSeen[man] = true
        }
    }

    if (neverHeard.size < neverSeen.size) {
        neverSeen.forEach {
            if (neverHeard[it.key] == true) never.add(it.key)
        }
    } else {
        neverHeard.forEach {
            if (neverSeen[it.key] == true) never.add(it.key)
        }
    }

    bw.write("${never.size}")
    never.sort()
    never.forEach {
        bw.write("$it\n")
    }

    bw.flush()
    bw.close()
}

fun anotherStringSet() = with(Scanner(System.`in`)) {
    val s = nextLine()
    val resultSet = mutableSetOf<String>()

    for (i in 0..s.length) {
        for (j in i + 1..s.length) {
            resultSet.add(s.substring(i, j))
        }
    }
    println(resultSet.size)
}

fun setNumber(x: String, y: String) {

    fun exchangeNum(s: String): IntArray {
        val digits = IntArray(s.length)
        for (i in s.indices) {
            digits[i] = s[i] - '0'
        }
        return digits
    }

    val xArray: IntArray = exchangeNum(x)
    val yArray: IntArray = exchangeNum(y)
    val equalArray = mutableListOf<Int>()

    xArray.forEach { if (it in yArray) equalArray.add(it) }

    if (equalArray.isEmpty()) {
        println("-1")
        return
    }

    xArray.sortedDescending()
    println(xArray.toString())
}

fun symmetricDifference() = with(Scanner(System.`in`)) {
    val (m, n) = readLine()!!.split(" ").map { it.toInt() }
    val aList = mutableListOf<Int>()
    val bList = mutableListOf<Int>()

    fun setList(c: Int, list: MutableList<Int>) {
        repeat(c) {
            list.add(nextInt())
        }
    }

    setList(m, aList)
    setList(n, bList)

    println(((aList - bList) + (bList - aList)).size)
}

//정규식 활용 이메일 형식 체크
fun isEmailValid(email: String): Boolean {
    var isValid = false
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val inputStr: CharSequence = email
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(inputStr)
    if (matcher.matches()) {
        isValid = true
    }
    return isValid
}

fun searchSameCard() = with(Scanner(System.`in`)) {
    val allCards = mutableListOf<Int>()
    val selectedCards = mutableListOf<Int>()
    val allCnt = nextInt()
    val resultCounts = mutableListOf<Int>()
    repeat(allCnt) {
        allCards.add(nextInt())
    }
    val selectCnt = nextInt()
    repeat(selectCnt) {
        selectedCards.add(nextInt())
    }

    selectedCards.forEach { selectNum ->
        var resultCnt = 0
        allCards.forEach { allNum ->
            if (selectNum == allNum) {
                resultCnt++
            }
        }
        resultCounts.add(resultCnt)
    }

    println(resultCounts)
}

fun parenthesisCheck() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()

    fun solve(str: String) {
        val stack = Stack<Char>()

        for (i in str) {
            if (i == '(') {
                stack.add(i)
            } else if (i == ')') {
                if (stack.isEmpty()) {
                    println("NO")
                    return
                }
                if (stack.pop() == ')') {
                    println("NO")
                    return
                }
            }
        }
        if (stack.isEmpty()) {
            println("YES")
        } else {
            println("NO")
        }
    }

    for (i in 0 until n) {
        val str = readLine()
        solve(str)
    }
}

//삼총사 - 프로그래머스 -> 세 수를 더하면 0이 나오는 모든 경우의 횟수를 구한다.
fun solution(number: IntArray): Int {
    var answer = 0
    for (i in number.indices) {
        for (j in i + 1 until number.size) {
            for (k in j + 1 until number.size) {
                if (number[i] + number[j] + number[k] == 0) {
                    answer++
                }
            }
        }
    }
    return answer
}

fun solution(n: Int): Int {
    var answer = 0
    for (i in 1..n) {
        if (i % 2 == 0) answer += i
    }
    return answer
}

//영화감독 숌
fun movieNumber() = with(System.`in`.bufferedReader()) {
    var n = readLine().toInt()
    var startNum = 665
    while (n != 0) {
        startNum++
        if (startNum.toString().contains("666")) {
            n--
        }
    }
    println(startNum)
}

//동전 0
fun soinsu() {
    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val coins = Array(n) { br.readLine().toInt() }
    var res = 0
    var left = k

    for (i in n - 1 downTo 0) {
        res += left / coins[i]
        left %= coins[i]
    }

    println(res)
}

//종이 자르기
fun solution(M: Int, N: Int): Int {
    return M * N - 1
}

fun solutionDemon(n: Int, t: Int): Int {
    var num = n
    var count = 0
    while (count != t) {
        num *= 2
        count++
    }
    return num
}

fun nearNumber(array: IntArray, n: Int): Int {
    var res = 0
    val calculateMap: MutableMap<Int, Int> = mutableMapOf()
    array.forEach { calculateMap[it] = abs(n - it) }
    val minValue = calculateMap.minOf { it.value }
    calculateMap.forEach { if (it.value == minValue) res = it.key }
    return res
}

fun colaFunc(a: Int = 3, b: Int = 0, n: Int = 20): Int {
    val takeColaList = mutableListOf<Int>()

    val first = (n / a) + (n % a)
    return first
}

fun afterParty() {
    val scanner = Scanner(System.`in`)
    val person = scanner.nextInt() //1m^2당 사람수

    val width = scanner.nextInt() //면적

    //기사별 사람 수
    //기사별 사람 수
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    val d = scanner.nextInt()
    val e = scanner.nextInt()

    if ((person >= 1 || person <= 10) && (width >= 1 || width <= 1000)) {
        val realCount = width * person
        print((a - realCount).toString() + " " + (b - realCount) + " " + (c - realCount) + " " + (d - realCount) + " " + (e - realCount) + " ")
    }
}

//직각거리 반환
fun distanceCheck(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() - 1 }
    //가로 거리는 몫으로, 세로 거리는 나머지로 구할 수 있다.
    println(abs(n / 4 - m / 4) + abs(n % 4 - m % 4))
}

fun candyTeacher() = with(Scanner(System.`in`)) {
    val t = nextInt()

    repeat(t) {
        val n = nextInt()
        var result = BigInteger.ZERO
        repeat(n) {
            result += nextBigInteger()
        }

        println(
            if (result % n.toBigInteger() == BigInteger.ZERO) "YES"
            else "NO"
        )
    }
}

fun checkFbi() = with(Scanner(System.`in`)) {
    val arr = arrayListOf<String>()
    repeat(5) {
        arr.add(next())
    }
    arr.forEachIndexed { index, s ->
        if (s.contains("FBI")) println(index + 1)
    }
}

/*
우선 평균을 구하는 법을 살펴보면 (앨범 내에 저작권이 있는 멜로디의 개수) / (앨범에 수록된 곡의 개수)이므로
저작권이 있는 멜로디의 개수는 (앨범에 수록된 곡의 개수) * 평균이다.
하지만 평균값을 항상 올림하므로 이렇게 계산한 값은 저작권이 있는 멜로디의 최대 개수가 된다.
따라서 저작권 멜로디의 최소 개수는 (곡의 개수) * (평균에서 1을 뺀 수)를 계산한 다음 1을 더해주면 된다.
멜로디의 개수가 1만 증가해도 값을 올림하게 되면 평균이 1 증가해 조건에 해당하기 때문이다.
 */
fun copyright() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = System.out.bufferedWriter()
    val st = StringTokenizer(br.readLine())

    val count = st.nextToken().toInt()
    val avg = st.nextToken().toInt()

    bw.write("${count * (avg - 1) + 1}\n")
    bw.flush()
    bw.close()
    br.close()
}

fun queueTest() {
    val arrayDeque = ArrayDeque<Int>()

    arrayDeque.add(1)
    arrayDeque.add(2)
    arrayDeque.addLast(3)
    arrayDeque.addFirst(0)

    println(arrayDeque)

    arrayDeque.removeFirst()

    println(arrayDeque)
}

//숫자 짝꿍
fun numberPair(x: String, y: String) {
    val pairList = mutableSetOf<String>()

    x.forEach { xNum ->
        if (xNum in y) pairList.add(xNum.toString())
    }

    val res = pairList.sortedByDescending { it.toInt() }
    var maxValueStr = "0"
    res.forEach { maxValueStr += it }

    println(maxValueStr.toInt())
}

//크기가 작은 부분 문자열
fun lessSizeStr(t: String, p: String): Int {
    var answer = 0
    for (i in 0 until t.length - p.length + 1) { // t 문자열을 분할해서 순회해야하는 횟수
        if (t.substring(i, i + p.length).toLong() <= p.toLong()) {
            answer++
        }
    }
    return answer
}

fun letterSize(msg: String): Int = msg.length * 2

fun prompt(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val files = mutableListOf<String>()
    val n = readLine().toInt()

    if (n == 1) {
        println(readLine())
        return@with
    } else {
        repeat(n) {
            files.add(readLine())
        }
    }

    val checkList = BooleanArray(files[0].length) { true }
    for (i in files[0].indices) for (j in 1 until n) {
        if (checkList[i]) checkList[i] = files[0][i] == files[j][i]
    }

    println(
        checkList.mapIndexed { i: Int, b: Boolean ->
            if (b) files[0][i]
            else '?'
        }.joinToString("")
    )
}

//추론
fun inference(args: Array<String>) {
    // input
    val f1 = readln()
    val f2 = readln()
    val f3 = readln()
    // output
    print(solution(f1, f2, f3))
}

fun solution(f1: String, f2: String, f3: String): Long {
    return (findResistanceVal(f1) * 10 + findResistanceVal(f2)) * findResistanceMul(f3)
}

const val kBlack = "black"
const val kBrown = "brown"
const val kRed = "red"
const val kOrange = "orange"
const val kYellow = "yellow"
const val kGreen = "green"
const val kBlue = "blue"
const val kViolet = "violet"
const val kGrey = "grey"
const val kWhite = "white"


fun findResistanceVal(color: String): Long {
    return when (color) {
        kBlack -> 0L
        kBrown -> 1L
        kRed -> 2L
        kOrange -> 3L
        kYellow -> 4L
        kGreen -> 5L
        kBlue -> 6L
        kViolet -> 7L
        kGrey -> 8L
        kWhite -> 9L
        else -> {
            -1L
        }
    }
}

fun findResistanceMul(color: String): Long {
    return when (color) {
        kBlack -> 1L
        kBrown -> 10L
        kRed -> 100L
        kOrange -> 1000L
        kYellow -> 10000L
        kGreen -> 100000L
        kBlue -> 1000000L
        kViolet -> 10000000L
        kGrey -> 100000000L
        kWhite -> 1000000000L
        else -> {
            -1L
        }
    }
}

fun throwBall(numbers: IntArray, k: Int): Int {
    var ansIndex = 0
    for (i in 0 until k) {
        ansIndex += 2
        if (ansIndex > numbers.size) {
            ansIndex -= numbers.size
        }
    }
    return numbers[ansIndex - 2]
}

fun pressStr(a: String, b: String): Int {
    var answer = 0
    var aa = a
    for (i in a.indices) {
        if (aa == b) return answer
        //마지막 문자를 잘라서 다시 맨 앞에 붙인다.
        val x = aa.substring(aa.length - 1)
        aa = x + aa.substring(0, aa.length - 1)
        answer++
    }
    return -1
}

fun oneShowChar(s: String): String {
    val charHash = hashMapOf<Char, Int>()
    s.forEach {
        if (!charHash.contains(it)) charHash[it] = 1
        else charHash[it] = charHash[it]!!.plus(1)
    }
    val resultArr = mutableListOf<Char>()
    charHash.forEach { (c, i) ->
        if (i == 1) resultArr.add(c)
    }
    return resultArr.toString()
}

//연속된 수의 합 -> 규칙;;
fun solutionHap(num: Int, total: Int): IntArray {
    val answer: IntArray = IntArray(num)
    val nSum = num * (num + 1) / 2
    val start = (total - nSum) / num

    for (i in 1..num) {
        answer[i - 1] = i + start
    }
    return answer
}

//합성수 구하기
fun hapSungSu(n: Int) = (1..n).filter { i -> (1..i).filter { i % it == 0 }.size > 2 }.size

fun checkNumberPattern(common: IntArray): Int {
    var res = 0

    res = if ((common[1] - common[0]) == (common[2] - common[1])) {
        common[common.size - 1] + (common[1] - common[0])
    } else {
        common[common.size - 1] * (common[1] / common[0])
    }
    return res
}

fun checkLogin(id_pw: Array<String>, db: Array<Array<String>>): String {
    val id = id_pw[0]
    val pw = id_pw[1]

    var idChecked = false
    var pwChecked = false

    db.forEach {
        if (it[0] == id) idChecked = true
        if (it[1] == pw) pwChecked = true
    }

    return if (idChecked && pwChecked) {
        "login"
    } else if (idChecked && !pwChecked) {
        "wrong pw"
    } else "fail"
}

fun combination(n: Int, r: Int): Int {
    return if (r == 0 || n == r) 1
    else combination(n - 1, r - 1) + combination(n - 1, r)
}

fun solutionCom(balls: Int, share: Int): Int {
    return combination(balls, share)
}

fun alienDic(spell: Array<String>, dic: Array<String>): Int {
    var isAvailable = false
    dic.forEach { word ->
        var checkCnt = 0
        spell.forEach {
            if (it in word) checkCnt += 1
        }
        if (checkCnt == word.length) isAvailable = true
    }
    return if (isAvailable) 1 else 2
}

//가장 가까운 글자
fun solution(s: String): Array<Int?> {
    val answer: Array<Int?> = arrayOfNulls(s.length)
    //맨 처음 글자는 무조건 -1
    answer[0] = -1

    for (i in s.indices) {
        val x = s.lastIndexOf(s.substring(i, i + 1), i - 1)

        //x 값이 -1이면, -1을 그대로 넣고, 아니라면 기존 위치 인덱스에서 존재하는 인덱스 값을 뺀다.
        if (x != -1) {
            answer[i] = i - x
        } else {
            answer[i] = x
        }
    }

    return answer
}

//제곱근 판별
fun checkSqrt(n: Int): Int {
    val sqrt = sqrt(n.toDouble()).toLong()
    return if (sqrt * sqrt == n.toLong()) 1 else 2
}

fun hateEng(numbers: String): Long {
    var numbers = numbers
    val arr =
        arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    for (i in arr.indices) {
        if (numbers.contains(arr[i])) {
            numbers = numbers.replace(arr[i].toRegex(), i.toString())
        }
    }
    return numbers.toLong()
}

fun changePosition(my_string: String, num1: Int, num2: Int): String {
    val charList = mutableListOf<Char>()

    my_string.forEach {
        charList.add(it)
    }

    val char1 = charList[num1]
    val char2 = charList[num2]

    charList[num2] = char1
    charList[num1] = char2

    var result = ""
    charList.forEach {
        result += it
    }

    return result
}

fun makeHamburger(ingredient: IntArray): Int {
    var result = 0
    val resultArr = intArrayOf(1, 2, 3, 1)
    val hamList = mutableListOf<Int>()

    ingredient.forEach {
        hamList.add(it)
        if (hamList.size >= 4) {
            val tmpList = mutableListOf<Int>()
            tmpList.add(hamList[hamList.size - 4])
            tmpList.add(hamList[hamList.size - 3])
            tmpList.add(hamList[hamList.size - 2])
            tmpList.add(hamList[hamList.size - 1])

            if (tmpList.equals(resultArr)) {
                result++
                hamList.remove(hamList.size - 1)
                hamList.remove(hamList.size - 1)
                hamList.remove(hamList.size - 1)
                hamList.remove(hamList.size - 1)
            }
        }
    }
    return result
}

fun checkTemp(size: Int, interval: Int) = with(Scanner(System.`in`)) {
    val arr = arrayListOf<Int>()
    repeat(size) {
        arr.add(nextInt())
    }

    val checkList = arrayListOf<Int>()
    for (i in 0 until arr.size) {
        checkList.add(arr[i] + arr[interval - 1])
    }
    println(checkList.max())
}

fun characterPosition(keyInput: ArrayList<String>, board: IntArray): IntArray {
    val answer: IntArray = intArrayOf(0, 0)
    val list = keyInput.toList()
    for (i in list) {
        when (i) {
            "left" -> {
                if (answer[0] <= -(board[0] / 2)) {
                    continue
                }
                answer[0] -= 1
            }
            "right" -> {
                if (answer[0] >= board[0] / 2) {
                    continue
                }
                answer[0] += 1
            }
            "up" -> {
                if (answer[1] >= board[1] / 2) {
                    continue
                }
                answer[1] += 1
            }
            "down" -> {
                if (answer[1] <= -(board[1] / 2)) {
                    continue
                }
                answer[1] -= 1
            }
        }
    }

    return answer
}

fun changeArrayDirection(numbers: IntArray, direction: String): IntArray {
    val tmpList = numbers.toList()
    if (direction == "right") {
        //배열의 전체 순서 전환
        Collections.rotate(tmpList, 1)
    } else {
        Collections.rotate(tmpList, -1)
    }
    return tmpList.toIntArray()
}

fun teamName() {

    fun calProbability(lCnt: Int, OCnt: Int, VCnt: Int, ECnt: Int) =
        ((lCnt + OCnt) * (lCnt + VCnt) * (lCnt + ECnt) * (OCnt + VCnt) * (OCnt + ECnt) * (VCnt + ECnt)) % 100

    var cntL = 0
    var cntO = 0
    var cntV = 0
    var cntE = 0
    var max = 0
    var resultTeam = ""

    val playerName = readln()
    playerName.forEach {
        when (it) {
            'L' -> cntL++
            'O' -> cntO++
            'V' -> cntV++
            'E' -> cntE++
        }
    }

    val teamCnt = readln().toInt()

    repeat(teamCnt) {
        var curLCnt = 0
        var curOCnt = 0
        var curVCnt = 0
        var curECnt = 0

        val teamName = readln()
        teamName.forEach {
            when (it) {
                'L' -> curLCnt++
                'O' -> curOCnt++
                'V' -> curVCnt++
                'E' -> curECnt++
            }
        }

        val curProbability =
            calProbability((cntL + curLCnt), (cntO + curOCnt), (cntV + curVCnt), (cntE + curECnt))

        //최대값까지 갱신
        if (max < curProbability) {
            max = curProbability
            resultTeam = teamName
        }

        if (max == curProbability) {
            if (resultTeam == "") resultTeam = teamName
            //사전순으로 출력
            resultTeam = listOf(resultTeam, teamName).sorted()[0]
        }
    }

    println(resultTeam)
}

//삼각형의 완성조건(1)
fun checkFit(sides: IntArray): Int {
    val list = sides.toList().sortedDescending()
    return if (list[1] + list[2] > list[0]) {
        1
    } else 2
}

//푸드 파이트 대회
fun foodFight(food: IntArray): String {
    val foodList = food.toList()
    val firsts = mutableListOf<Int>()
    val seconds = mutableListOf<Int>()
    for (i in foodList.indices) {
        if (i > 0) {
            val foodCnt = foodList[i]
            if (foodCnt % 2 != 0) {
                repeat((foodCnt - 1) / 2) {
                    firsts.add(i)
                    seconds.add(i)
                }
            } else {
                repeat(foodCnt / 2) {
                    firsts.add(i)
                    seconds.add(i)
                }
            }
        }
    }
    firsts.sort()
    seconds.sortDescending()
    val result = firsts + 0 + seconds
    return result.toString()
}

fun cardPack(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    val firstList = cards1.toMutableList()
    val res = mutableListOf<String>()

    res.add(firstList[0])
    res.addAll(cards2)
    res.add(firstList[1])
    res.add(firstList[2])

    fun isEqual(first: List<String>?, second: List<String>?): Boolean {
        if (first == null || second == null) {
            return false
        }
        if (first.size != second.size) {
            return false
        }
        for (i in first.indices) {
            if (first[i] != second[i]) {
                return false
            }
        }
        return true
    }

    val result = isEqual(res, goal.toMutableList())
    return if (result) {
        "Yes"
    } else "No"
}

fun cutChoco(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val inputs = readLine().split(" ")
    println(inputs[0].toInt() * inputs[1].toInt() - 1)
}

fun check2007() {
    val scan = Scanner(System.`in`)

    val month = scan.nextInt()
    val day = scan.nextInt()

    var sum = 0

    //2007년의 각 월 일 수들
    val months = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    for (i in 1..12) {
        if (i == month) {
            break
        }
        sum += months[i]
    }

    sum += day

    when (sum % 7) {
        0 -> println("SUN")
        1 -> println("MON")
        2 -> println("TUE")
        3 -> println("WED")
        4 -> println("THU")
        5 -> println("FRI")
        6 -> println("SAT")
    }
}

//외계어 사전
fun alienDic() {
    fun solution(spell: Array<String>, dic: Array<String>) =
        if (dic.map { it.toList().sorted().joinToString("") }
                .contains(spell.sortedArray().joinToString(""))) 1 else 2
}

//다항식 더하기
fun plusLine(polynomial: String): String {
    var xCnt = 0
    var num = 0

    for (s in polynomial.split(" ".toRegex())) {
        if (s.contains("x")) {
            xCnt += if (s == "x") 1 else s.replace("x".toRegex(), "").toInt()
        } else if (s != "x") num += s.toInt()
    }
    return (if (xCnt != 0) if (xCnt > 1) "${xCnt}x" else "x" else "") + if (num != 0) (if (xCnt != 0) " + " else "") + num else if (xCnt == 0) "0" else ""
}

fun deleteSelectedChar(my_string: String, letter: String): String {
    var res = ""
    letter.toCharArray().forEach {
        res = my_string.replace(it.toString(), "")
    }
    return res
}

fun ants(hp: Int): Int = hp / 5 + (hp % 5 / 3) + (hp % 5 % 3)

fun chickenCoupon(chicken: Int): Int {
    var order = chicken
    var res = 0
    while (order >= 10) {
        res += order / 10
        order = (order / 10) + (order % 10)
    }
    return res
}

fun mosCheck(letter: String): String {
    val dictionary = mapOf(
        ".-" to "a",
        "-..." to "b",
        "-.-." to "c",
        "-.." to "d",
        "." to "e",
        "..-." to "f",
        "--." to "g",
        "...." to "h",
        ".." to "i",
        ".---" to "j",
        "-.-" to "k",
        ".-.." to "l",
        "--" to "m",
        "-." to "n",
        "---" to "o",
        ".--." to "p",
        "--.-" to "q",
        ".-." to "r",
        "..." to "s",
        "-" to "t",
        "..-" to "u",
        "...-" to "v",
        ".--" to "w",
        "-..-" to "x",
        "-.--" to "y",
        "--.." to "z"
    )
    return letter.split(" ".toRegex()).map { dictionary[it] }.joinToString("")
}

//순서쌍의 개수
fun cntNumPair(n: Int) = (1..n).count { n % it == 0 }

//최대값 구하기 2
fun maxTwo(numbers: IntArray): Int {
    val list = numbers.toList()
    val res = list.flatMapIndexed { index: Int, i: Int ->
        list.subList(index + 1, list.size).map { b -> i * b }
    }.maxOf { it }
    return res
}

//두 번째 문자열을 더해서 첫 번째 문자열의 인덱스를 구하면 된다. (WOW)
fun pushString(A: String, B: String): Int = (B + B).indexOf(A)

//fun lineLength(lines: Array<IntArray>): Int  {
//    var answer: Int = 0
//
//    val map: MutableMap<String, Int> = HashMap()
//
//    for (line in lines) {
//
//    }
//}

fun weirdArray(numlist: IntArray, n: Int): IntArray {
    val res = mutableListOf<Int>()
    val arr = numlist.toMutableList()

    res.add(n)

    while (true) {
        if (arr.isEmpty()) break
        val startPos = arr.indexOf(n)
        if (arr.size >= 3) {
            res.add(maxOf(arr[startPos - 1], arr[startPos + 1]))
            arr.removeAt(startPos - 1)
            arr.removeAt(startPos + 1)
        } else {
            arr.remove(n)
            res.addAll(arr)
        }
    }

    return res.toIntArray()
}

fun weirdArrayResult(numlist: IntArray, n: Int): IntArray {
    return numlist.sortedWith { a, b ->
        //중간 값과의 차이 값을 통해 비교한다.
        if (abs(a - n) == abs(b - n)) b.compareTo(a) else abs(a - n).compareTo(abs(b - n))
    }.toIntArray()
}

fun hideNumberPlus(my_string: String): Int {
    var res = 0
    val list = my_string.split("[a-zA-z]+".toRegex()).toTypedArray()
    for (i in list.indices) {
        if (list[i].matches("[0-9]+".toRegex())) res += list[i].toInt()
    }
    return res
}

fun factorialCheck(n: Int): Int {
    var res = 0

    //재귀 함수
    fun factorial(n: Int): Int {
        return if (n == 1) n
        else n * factorial(n - 1)
    }

    for (i in 10 downTo 1) {
        if (factorial(i) <= n) {
            res = i
            break
        }
    }
    return res
}

fun findK(array: IntArray, commands: Array<IntArray>): IntArray {
    val res = IntArray(commands.size)
    for (i in res.indices) {
        var tempArr = array.slice(commands[i][0] - 1 until commands[i][1])
        tempArr = tempArr.sorted()
        res[i] = tempArr[commands[i][2] - 1]
    }
    return res
}