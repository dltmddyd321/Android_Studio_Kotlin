@file:Suppress("BlockingMethodInNonBlockingContext")

package com.sycompany.bojstep

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.Arrays
import java.util.Calendar
import java.util.Collections
import java.util.LinkedList
import java.util.Locale
import java.util.PriorityQueue
import java.util.Queue
import java.util.Scanner
import java.util.Stack
import java.util.StringTokenizer
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sqrt


//val indexedIntervals = intervals.mapIndexed { index, interval ->
//        Triple(index, interval[0], interval[1])
//    }.sortedBy { it.second }
//for (interval in indexedIntervals) {
//    val (_, _, end) = interval
//    // 이진 탐색을 통해 가장 작은 시작 지점이 end 이상인 구간 찾기
//    val rightIntervalIndex = indexedIntervals.binarySearch { it.second.compareTo(end) }
//    val adjustedIndex = if (rightIntervalIndex < 0) -rightIntervalIndex - 1 else rightIntervalIndex
//
//    if (adjustedIndex < indexedIntervals.size) {
//        result[interval.first] = indexedIntervals[adjustedIndex].first
//    }
//}

@RequiresApi(Build.VERSION_CODES.N)
fun solution(n: Int, k: Int, enemy: IntArray): Int {
    var soldiers = n
    var items = k
    //라운드별로 병사를 통해 막은 적의 수를 저장하는 큐
    val heap = PriorityQueue<Int>(compareByDescending { it })

    for (i in enemy.indices) {
        val target = enemy[i]
        if (soldiers >= target) { //적의 데미지만큼 차감
            soldiers -= target
            heap.offer(target)
        } else {
            if (items > 0) {
                if (heap.isNotEmpty() && heap.peek()!! > target) {
                    soldiers += heap.poll()!! - target
                    heap.offer(target)
                }
                items --
            } else {
                return i
            }
        }
    }
    return enemy.size
}

fun uniquePaths(m: Int, n: Int): Int {
    val dp = Array(m) { IntArray(n) }

    // 첫 번째 행과 첫 번째 열은 경로가 1개밖에 없음
    for (i in 0 until m) {
        dp[i][0] = 1
    }
    for (j in 0 until n) {
        dp[0][j] = 1
    }

    // 나머지 칸의 경로 수 계산
    for (i in 1 until m) {
        for (j in 1 until n) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        }
    }

    return dp[m - 1][n - 1]
}

fun solution(picks: IntArray, minerals: Array<String>): Int {
    var answer = 0

    //마인이 작업을 끝내기까지 필요한 최소한의 피로도를 return
    val cnt = min((minerals.size / 5 + 1).toDouble(), (picks[0] + picks[1] + picks[2]).toDouble())
        .toInt()
    val section = Array(cnt) { IntArray(3) } //5개씩 묶었을 때 광물별 피로도 계산
    var dp = 0
    var ip = 0
    var sp = 0

    //곡괭이 개수만큼만 세기 -> 어차피 곡괭이 수가 부족하면 뒤에 있는 광물은 못 캠.
    run {
        var i = 0
        while (i < minerals.size) {
            if (i / 5 == cnt) {
                break
            }
            for (j in i until i + 5) {
                val m = minerals[j]
                when (m) {
                    "diamond" -> {
                        dp += 1
                        ip += 5
                        sp += 25
                    }
                    "iron" -> {
                        dp += 1
                        ip += 1
                        sp += 5
                    }
                    else -> {
                        dp += 1
                        ip += 1
                        sp += 1
                    }
                }

                if (j == minerals.size - 1) {
                    break
                }
            }

            section[i / 5][0] = dp
            section[i / 5][1] = ip
            section[i / 5][2] = sp

            sp = 0
            ip = sp
            dp = ip
            i += 5
        }
    }


    //돌로 캤을 때 피로도가 가장 높은 순으로 내림차순 정렬
    Arrays.sort(section) { o1: IntArray, o2: IntArray -> (o2[2] - o1[2]) }


    //다이아 -> 철 -> 돌 순서대로 캐기
    for (i in 0 until cnt) {
        if (picks[0] != 0) {
            answer += section[i][0] //다이아로 캤을 때 피로도
            picks[0]--
        } else if (picks[1] != 0) {
            answer += section[i][1]
            picks[1]--
        } else if (picks[2] != 0) {
            answer += section[i][2]
            picks[2]--
        }
    }

    return answer
}

fun lengthOfLIS(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val sub = mutableListOf<Int>()

    for (num in nums) {
        // 이진 탐색으로 현재 num이 들어갈 위치를 찾습니다.
        var left = 0
        var right = sub.size

        while (left < right) {
            val mid = (left + right) / 2
            if (sub[mid] < num) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        // 만약 num이 sub의 모든 원소보다 크다면, sub 리스트에 추가합니다.
        // 그렇지 않다면, 적절한 위치에 값을 갱신합니다.
        if (left < sub.size) {
            sub[left] = num
        } else {
            sub.add(num)
        }
    }

    // 최종적으로 sub 리스트의 크기가 가장 긴 증가하는 부분 수열의 길이가 됩니다.
    return sub.size
}

fun inequalityCheck() {
    val k = readln().toInt()
    val signs = readln().split(" ")

    val used = BooleanArray(10)
    val results = mutableListOf<String>()

    fun check(a: Int, b: Int, sign: String): Boolean {
        return if (sign == "<") {
            a < b
        } else {
            a > b
        }
    }

    fun backtracking(num: String, depth: Int) {
        if (depth == k + 1) {
            results.add(num)
            return
        }

        for (i in 0..9) {
            if (!used[i]) {
                if (depth == 0 || check(num[depth - 1].toInt(), i, signs[depth - 1])) {
                    used[i] = true
                    backtracking(num + i, depth + 1)
                    used[i] = false
                }
            }
        }
    }

    backtracking("", 0)

    results.sort()
    println(results.last())
    println(results.first())
}

fun targetNumber(numbers: IntArray, target: Int): Int {
    var answer = 0

    fun dfs(index: Int, currentSum: Int) {
        // 모든 숫자를 사용한 경우
        if (index == numbers.size) {
            if (currentSum == target) {
                answer++
            }
            return
        }

        // 다음 숫자를 더한 경우
        dfs(index + 1, currentSum + numbers[index])
        // 다음 숫자를 뺀 경우
        dfs(index + 1, currentSum - numbers[index])
    }

    dfs(0, 0)

    return answer
}

fun main() {
    val s = Scanner(System.`in`)
    val n = s.nextInt()
    s.nextLine()
    val arr = s.nextLine().split(" ").map { it.toInt() }
    val start = s.nextInt()

    val visited = BooleanArray(n) { false }
    var cnt = 0

    fun dfs(position: Int) {
        //방문 여부 확인
        if (visited[position]) return
        visited[position] = true
        cnt++

        val left = position - arr[position]
        val right = position + arr[position]

        if (left >= 0) dfs(left)
        if (right < n) dfs(right)
    }

    dfs(start - 1)

    println(cnt)
}

fun stackSolution(s: String): Int {
    val openBracket = charArrayOf('(', '{', '[')
    val closeBracket = charArrayOf(')', '}', ']')
    var res = 0

    val queue: Queue<Char> = LinkedList()
    s.forEach { queue.add(it) }

    fun isSuccess(s: String): Boolean {
        val stack = Stack<Char>()
        var flag: Boolean
        for (c in closeBracket) if (c == s[0]) return false

        for (c in s) {
            flag = false
            for (i in openBracket.indices) {
                if (stack.isNotEmpty() && stack.peek() == openBracket[i] && c == closeBracket[i]) flag =
                    true
            }
            if (flag) stack.pop() else stack.add(c)
        }
        return stack.isEmpty()
    }

    repeat(s.length) {
        if (isSuccess(queue.joinToString(""))) res++
        val last = queue.remove()
        queue.offer(last)
    }
    return res
}

fun discountSolution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
    val itemMap = mutableMapOf<String, Int>()
    for (i in want.indices) {
        itemMap[want[i]] = number[i]
    }

    var res = 0
    var startIndex = 0
    while (startIndex <= discount.lastIndex - 10) {
        val temp = discount.toMutableList().subList(startIndex, startIndex + 10)
        val tempMap = temp.groupingBy { it }.eachCount()
        if (tempMap == itemMap) res++
        startIndex++
    }
    return res
}

fun bigNumber() {
    fun solution(number: String, k: Int): String {
        val result = CharArray(number.length - k)
        var top = 0
        var count = k

        for (i in number.indices) {
            val char = number[i]
            // 스택의 마지막 요소가 현재 숫자보다 작고, 아직 제거할 수 있는 횟수가 남았다면 제거
            while (top > 0 && result[top - 1] < char && count > 0) {
                top--
                count--
            }
            // 결과 배열의 크기가 다 차지 않았다면 현재 숫자를 결과에 추가
            if (top < result.size) {
                result[top++] = char
            }
        }

        return String(result)
    }
}

fun magicalElevator(storey: Int): Int {
    var temp = storey
    var cnt = 0

    while (temp > 0) {
        //제일 작은 자릿 수부터 계산하도록 10으로 나눈다.
        var remain = temp % 10
        //temp 값은 작은 자릿 수를 빼야하니 10으로 나눈 몫으로 변경한다.
        temp /= 10

        when {
            remain > 5 -> { //반올림 처리
                temp++
                cnt += 10 - remain
            }

            remain == 5 -> { //다음 자릿수에 따라 올림, 내림 처리가 결정된다.
                if (temp % 10 >= 5) {
                    temp++
                    cnt += 10 - remain
                } else {
                    cnt += remain
                }
            }

            else -> {
                cnt += remain
            }
        }
    }
    return cnt
}

fun solution(clothes: Array<Array<String>>): Int {
    val arr = mutableMapOf<String, Int>()

    clothes.forEach {
        arr[it[1]] = (arr[it[1]] ?: 1) + 1
    }
    return arr.values.fold(1) { acc, i ->
        acc * i
    } - 1
}

fun solutionVowels(word: String): Int {
    val vowels = listOf('A', 'E', 'I', 'O', 'U')
    val result = mutableListOf<String>()

    fun backtrack(currentWord: String) {
        if (currentWord.length <= 5) {
            if (currentWord.isNotEmpty()) {
                result.add(currentWord)
            }
            for (vowel in vowels) {
                backtrack(currentWord + vowel)
            }
        }
    }
    backtrack("")

    return result.indexOf(word) + 1
}

fun main11111() = with(BufferedReader(InputStreamReader(System.`in`))) {

    fun lowerBound(cards: List<Int>, target: Int): Int {
        var left = 0
        var right = cards.size

        while (left < right) {
            val mid = (left + right) / 2
            if (cards[mid] >= target) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    fun upperBound(cards: List<Int>, target: Int): Int {
        var left = 0
        var right = cards.size

        while (left < right) {
            val mid = (left + right) / 2
            if (cards[mid] > target) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }

    val n = readLine().toInt()
    val cards = readLine().split(" ").map { it.toInt() }.sorted()
    val m = readLine().toInt()
    val queries = readLine().split(" ").map { it.toInt() }

    val result = StringBuilder()

    for (query in queries) {
        val lowerBound = lowerBound(cards, query)
        val upperBound = upperBound(cards, query)
        result.append("${upperBound - lowerBound} ")
    }

    println(result.toString().trim())
}

fun solution(citations: IntArray): Int {
    val list = citations.sortedDescending()
    for (i in list.indices) {
        if (list[i] <= i) return i
    }
    return list.size
}

@RequiresApi(Build.VERSION_CODES.N)
fun orderQueueTest(operations: Array<String>): IntArray {
    val answer = intArrayOf(0, 0)
    val maxQue = PriorityQueue<Int>(Collections.reverseOrder())
    val minQue = PriorityQueue<Int>()

    for (input in operations) {
        val op: List<String> = input.split(" ")
        when (op[0]) {
            "I" -> {
                val num: Int = Integer.parseInt(op[1])
                maxQue.offer(num)
                minQue.offer(num)
            }

            "D" -> {
                if (maxQue.isEmpty()) continue
                when (op[1]) {
                    "1" -> {
                        val max = maxQue.poll()
                        minQue.remove(max)
                    }

                    "-1" -> {
                        val min = minQue.poll()
                        maxQue.remove(min)
                    }
                }
            }
        }
    }
    if (!maxQue.isEmpty() && !minQue.isEmpty()) {
        answer[0] = maxQue.poll()!!
        answer[1] = minQue.poll()!!
    }
    return answer
}

fun solution(scoville: IntArray, k: Int): Int {
    val list = PriorityQueue(scoville.toList())
    var res = 0

    while (list.any { it <= k }) {
        res++
        val first = list.poll() ?: break
        val second = list.poll() ?: break
        list.add(first + (second * 2))
    }

    return if (res == 0) -1 else res
}

fun mainConvert() {
    data class AttributesFirst(
        val id: Int,
        val name: String,
        val url: String
    )

    data class AttributesSecond(
        val id: Int,
        val label: String,
        val score: Double
    )

    data class Result(
        val isSuccess: Boolean,
        val data: HashMap<String, Any>
    )


    val hashMap = mutableMapOf<String, Any>()
    hashMap["id"] = 1
    hashMap["name"] = "Adam"
    hashMap["url"] = "https://www.naver.com"

    // Map을 JSON 문자열로 변환
    val gson = Gson()
    val json = gson.toJson(hashMap)

    // JSON 문자열을 데이터 클래스로 변환
    val user: AttributesFirst = gson.fromJson(json, AttributesFirst::class.java)
    println("Converted User: $user")
}

fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
    val answer = mutableListOf<Int>()
    val term = mutableMapOf<String, Int>()
    val todayInt = today.replace(".", "").toInt()

    terms.map {
        val (type, date) = it.split(" ")
        term[type] = date.toInt()
    }

    privacies.mapIndexed { i, it ->
        val (date, type) = it.split(" ")
        var year = term[type]!! / 12
        if (year < 1)
            year = 0

        val month = term[type]!! % 12
        val expiredDate = (date.replace(".", "").toInt() + year * 10000 + month * 100).toString()

        var temp = expiredDate.toInt()
        if (expiredDate[4].toString().toInt() * 10 + expiredDate[5].toString().toInt() > 12) {
            temp -= 1200
            temp += 10000
        }

        if (temp <= todayInt) {
            answer.add(i + 1)
        }
    }

    return answer.toIntArray()
}

fun stringToCalendar(dateString: String, addMonth: Int? = null): Calendar {
    val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        val date = sdf.parse(dateString)
        if (date != null) calendar.time = date
    } catch (e: Exception) {
        e.printStackTrace()
    }
    if (addMonth != null) calendar.add(Calendar.MONTH, addMonth)
    return calendar
}

fun solution(survey: Array<String>, choices: IntArray): String {
    var answer: String = ""

    val person: CharArray = charArrayOf('R', 'T', 'C', 'F', 'J', 'M', 'A', 'N')
    val score: IntArray = IntArray(8) { 0 }

    for (i in survey.indices) {
        score[person.indexOf(survey[i][1])] += choices[i] - 4
    }

    for (i in 0..7 step 2) {
        answer += if (score[i] >= score[i + 1]) person[i] else person[i + 1]
    }
    return answer
}

@SuppressLint("DefaultLocale")
fun main23132() {
    // 주어진 2행 4열의 배열 예시
    val array = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8)
    )

    // 가로 평균 계산
    val rowAverages = array.map { row -> row.average() }
    println(rowAverages.joinToString(" ") { String.format("%.1f", it) })

    // 세로 평균 계산
    val colAverages = DoubleArray(4)
    for (j in 0 until 4) {
        var sum = 0.0
        for (i in 0 until 2) {
            sum += array[i][j]
        }
        colAverages[j] = sum / 2
    }
    println(colAverages.joinToString(" ") { String.format("%.1f", it) })

    // 전체 평균 계산
    val totalSum = array.sumOf { it.sumOf { it } }
    val totalAverage = totalSum.toDouble() / (2 * 4)
    println(String.format("%.1f", totalAverage))
}

fun main4123234() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val arr = Array(n) { st.nextToken().toInt() }
    val dp = Array(n) { 0 }

    dp[0] = arr.first()
    var max = arr.first()

    for (i in 1 until n) {
        dp[i] = max(dp[i - 1] + arr[i], arr[i])
        max = max(max, dp[i])
    }
    println(max)
}

fun mainCheck() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val dp = IntArray(11)
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for (i in 4..10) {
        dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1]
    }

    val str = StringBuilder()
    val a = br.readLine().toInt()

    repeat(a) {
        str.append("${dp[br.readLine().toInt()]}\n")
    }

    bw.write("$str")
    bw.flush()
    bw.close()
    br.close()
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
fun solution111(number: IntArray): Int {
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

fun freeStringSort(strings: Array<String>, n: Int): Array<String> {
    return strings.sortedWith { a, b ->
        if (a[n] == (b[n])) a.compareTo(b)
        else a[n].compareTo(b[n])
    }.toTypedArray()
}

fun mostNearWord(s: String): IntArray {

//    val answer = IntArray(s.length)
//
//    for (i in s.indices) {
//        if (i != 0) {
//            val idx: Int = s.substring(0, i)
//                .lastIndexOf(s.charAt(i))
//            if (idx != -1) {
//                answer[i] = i - idx
//            } else {
//                answer[i] = idx
//            }
//        } else {
//            answer[i] = -1
//        }
//    }

//    return answer

//    val res = mutableListOf<Int>()
//    repeat(s.length) {
//        res.add(0)
//    }
//    //맨 처음 글자는 무조건 최초 글자이므로 -1
//    res[0] = -1
//    for (i in 1..s.length) {
//        //인덱스를 순회하며 i-1을 기준으로 값이 중복되는지 여부를 확인
//        val tmp = s.lastIndexOf(s.substring(i, i + 1), i - 1)
//        if (tmp != -1) {
//            //기존 위치의 인덱에서 존재하는 중복 인덱스 위치를 차감
//            res[i] = i - tmp
//        } else {
//            res[i] = tmp
//        }
//    }
//    return res.toIntArray()

    val charMap = mutableMapOf<Char, Int>()

    return s.withIndex().map { (i, char) ->
        val check = charMap[char]
        charMap[char] = i
        if (check == null) -1 else i - check
    }.toIntArray()
}

//이진수 더하기
fun parseIntTwo(bin1: String, bin2: String): String =
    Integer.toBinaryString(Integer.parseInt(bin1, 2) + Integer.parseInt(bin2, 2))

//3진법 뒤집기
fun ternaryFlip(n: Int): Int = n.toString(3).reversed().toInt(3)

fun rectangleSizeFromDots(dots: Array<IntArray>): Int {
    val xDots = dots.map { it[0] }.toSortedSet()
    val yDots = dots.map { it[1] }.toSortedSet()

    return (xDots.last() - xDots.first()) * (yDots.last() - yDots.first())
}

fun balanceWorld() = with(Scanner(System.`in`)) {
    val stack = Stack<Char>()

    while (true) {
        val str = readln()

        if (str == ".") return@with

        str.forEach { ch ->
            //스택에 있는 마지막 문자 가져오기
            val lastElement = if (stack.isNotEmpty()) stack.peek() else null

            if (ch == '[' || ch == ']' || ch == '(' || ch == ')') {
                if (lastElement == '[') {
                    if (ch == ']') stack.pop() //순서쌍이 맞으므로 스택에서 제거
                    else stack.add(ch)
                } else if (lastElement == '(') {
                    if (ch == ')') stack.pop() //순서쌍이 맞으므로 스택에서 제거
                    else stack.add(ch)
                } else stack.add(ch) //다음 체크를 위해 스택에 추가
            }
        }
        if (stack.isEmpty()) println("yes") else println("no")
        stack.clear()
    }
}

fun trialTest(answers: IntArray): IntArray {
    val userAnswers = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
    )
    val correctCnt = IntArray(3)
    val res = mutableListOf<Int>()

    userAnswers.forEachIndexed { i, arr ->
        correctCnt[i] = answers.filterIndexed { j, answer -> answer == arr[j % arr.size] }.count()
    }

    correctCnt.forEachIndexed { index, i -> if (correctCnt.maxOrNull() == i) res.add(index + 1) }
    return res.toIntArray()
}

fun checkSolution(answers: IntArray): IntArray {
    val userAnswers = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
    )
    val cnt = IntArray(3)
    val answer = mutableListOf<Int>()

    userAnswers.forEachIndexed { i, userAns ->
        cnt[i] = answers.filterIndexed { j, ans -> ans == userAns[j % userAns.size] }.count()
    }
    cnt.forEachIndexed { idx, i -> if (cnt.max() == i) answer.add(idx + 1) }

    return answer.toIntArray()
}

@SuppressLint("NewApi")
fun getSixMonthAgo(): Long {
    return OffsetDateTime.now(ZoneId.systemDefault()).minusMonths(6).toInstant().toEpochMilli()
}

//queue 는 first in last out 이다.
fun queueWithLibrary() {
    val q: Queue<Int> = java.util.LinkedList()
    q.add(1) // 큐가 가득찬 상태이면 exception 발생
    q.offer(1) // 큐가 가득찬 상태이면 false 반환
    q.remove() // 삭제하면서 객체 반환 비어있는 상태이면 exception 발생
    q.poll() // 삭제하면서 객체 반환 비어있는 상태이면 false 반환
}

fun queueWithArrayList() {
    var q = arrayListOf<Int>()
    q.add(1) //뒤로 넣고
    q.removeFirst() //앞에서 뺀다
}

//stack 은 first in first out 이다.
fun stackWithLibrary() {
    var s = Stack<Int>()
    s.push(1) //큐에 추가
    s.peek() //빼지 않고 제일 위의 것 확인하기
    s.pop() //큐에서 빼기
}

fun stackWithArrayList() {
    var s = arrayListOf<Int>()
    s.add(1) //뒤로 넣고
    s.removeLast() //뒤에서 뺀다
    s.get(s.size - 1) // peek
}

//이진 트리 형태의 자료 구조로, 오름차순/내림차순으로 값을 저장한다. 값에서 peek 를 사용해 최대값/최소값을 얻을 수 있다.
@RequiresApi(Build.VERSION_CODES.N)
fun priorityQueue() {
    val pqAscending = PriorityQueue<Int>()
    val pqDescending = PriorityQueue<Int>(Collections.reverseOrder())
    val pqCustomOrder = PriorityQueue<Pair<Int, String>>(kotlin.Comparator { o1, o2 ->
        when {
            o1.first != o2.first -> when {
                o1.first < o2.first -> 1    //1 -> o2를 높은 우선순위로 둔다
                o1.first > o2.first -> -1   //-1 -> o1을 높은 우선 순위로 둔다
                else -> 0                   //0 -> 같은 우선 순위로 둔다
            }

            else -> when {
                o1.second < o2.second -> 1
                o1.second > o2.second -> -1
                else -> 0
            }
        }
    })
    pqAscending.addAll(listOf(1, 3, 5, 6, 6))
    pqDescending.addAll(listOf(1, 3, 5, 6, 6))

    pqAscending.add(4)
    pqDescending.offer(4)

    pqAscending.contains(5) // 확인

    pqAscending.peek() // 1
    pqDescending.peek() // 6

    pqAscending.poll()
    pqDescending.remove()
}

fun basicSort() {
    val aar = arrayListOf<Pair<Int, Int>>()
    aar.sortWith { o1, o2 ->
        if (o1.second == o2.second) { //second 끼리 같으면 first 를 비교하고
            o1.first.compareTo(o2.first)
        } else {
            o1.second.compareTo(o2.second) //다르면 second 를 비교한다.
        }
    }
}
