package com.example.programmerstest

import android.util.TypedValue
import android.widget.TextView
import java.lang.String.join
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.max
import kotlin.math.sqrt


fun main() {
    val arr = intArrayOf(10, 100, 20, 150, 1, 100, 200)
    solution(3, arr).forEach {
        println(it)
    }
}

fun solutionSecond(nums: IntArray): Int {
    var answer = 0

    fun isPrime(number: Int): Boolean {
        if (number <= 1) {
            return false
        }

        for (i in 2 until number) {
            if (number % i == 0) {
                return false
            }
        }

        return true
    }

    for (i in 0 until nums.size - 2) {
        for (j in i + 1 until nums.size - 1) {
            for (k in j + 1 until nums.size) {
                if (isPrime(nums[i] + nums[j] + nums[k])) answer ++
            }
        }
    }

    return answer
}

fun solutionSecond(number: Int, limit: Int, power: Int): Int = (1..number).map { i ->
    val x = (1..sqrt(i.toFloat()).toInt()).filter { i % it == 0 }
    (x + x.map { i / it }).toSet().count()
}.map {
    if (it > limit) power else it
}.sumOf { it }

fun solutionThird(number: Int, limit: Int, power: Int) = (1..number)
    .map {
        (1..it).count { each -> it % each == 0 } //약수의 개수를 구한다.
    }.sumOf {
        if (it > limit) power else it
    }

fun solution(number: Int, limit: Int, power: Int): Int {

    val temp = mutableListOf<Int>()
    val res = mutableListOf<Int>()

    fun countDivisors(number: Int): Int {
        var count = 0

        for (i in 1..number) {
            if (number % i == 0) {
                count++
            }
        }

        return count
    }

    fun sumOfDivisors(number: Int): Int {
        var sum = 0

        for (i in 1..number) {
            if (number % i == 0) {
                sum += i
            }
        }

        return sum
    }

    repeat(number) { temp.add(countDivisors(it + 1)) }

    temp.forEachIndexed { index, i ->
        if (i > limit) res.add(index + 1)
    }

    var result = 0
    res.forEach {
        result += sumOfDivisors(it)
    }

    return if (res.isEmpty()) {
        temp.sum()
    } else result
}

fun solution(k: Int, score: IntArray): IntArray {
    val answer = IntArray(score.size)
    val kList = IntArray(k)
    for (i in score.indices) {
        if (i < k) {
            kList[i] = score[i]
            kList.sortDescending()
            answer[i] = kList[i]
        } else {
            if (kList[k - 1] < score[i]) kList[k - 1] = score[i]
            kList.sortDescending()
            answer[i] = kList[k - 1]
        }
    }
    return answer
}

fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    var answer = n
    // set을 사용하여 여벌옷이 있는 사람을 제외하고 추가
    val lostSet = lost.sorted().toSet() - reserve.toSet()
    // set을 사용하여 잃어버린 사람을 제외하고 추가
    val reserveSet = (reserve.toSet() - lost.toSet()) as MutableSet

    for (l in lostSet) {
        when {
            // 이전 사람이 여벌옷이 있는 경우
            l - 1 in reserveSet -> reserveSet.remove(l - 1)
            // 앞 사람이 여벌옷이 있는 경우
            l + 1 in reserveSet -> reserveSet.remove(l + 1)
            // 체육복을 구하지 못한경우 answer 감소
            else -> answer--
        }
    }

    return answer
}

fun foodSol(food: IntArray): String {
//    var size = 1
//    for ((index, i) in food.withIndex()) {
//        if (index == 0) continue
//        size += if (i % 2 == 0) {
//            i
//        } else {
//            i - 1
//        }
//    }
//    val result = arrayOfNulls<Int>(size).toMutableList()
//    result.add(size / 2, 0)
    val foodCnt = food.slice(1 until food.size).map { it / 2 }
    val individualFood = mutableListOf<Int>()
    for (i in foodCnt.indices) {
        for (j in 0 until foodCnt[i]) {
            if (foodCnt[i] != 0) {
                individualFood.add(i + 1)
            }
        }
    }
    return individualFood.joinToString("") + "0" + individualFood.reversed().joinToString("")
}

fun solution(X: String, Y: String): String {

    fun checkNoCommonElements(array1: IntArray, array2: IntArray): Boolean {
        val combinedArray = array1 + array2
        val uniqueElementsCount = combinedArray.toSet().size
        val totalLength = array1.size + array2.size

        return uniqueElementsCount == totalLength
    }

    fun findCommonPairs(array1: IntArray, array2: IntArray): MutableList<Int> {
        val commonPairs = mutableListOf<Int>()

        if (checkNoCommonElements(array1, array2)) return emptyList<Int>().toMutableList()

        for (element in array1.toSet()) {
            val countInArray1 = array1.count { it == element }
            val countInArray2 = array2.count { it == element }

            val commonCount = minOf(countInArray1, countInArray2)

            repeat(commonCount) {
                commonPairs.add(element)
            }
        }

        return commonPairs
    }

    val xArr = X.map { it.toString().toInt() }.toIntArray()
    val yArr = Y.map { it.toString().toInt() }.toIntArray()
    val checkedList = findCommonPairs(xArr, yArr)
    val temp = mutableListOf<Int>()


    if (checkedList.all { it == 0 }) temp.add(0)
    else if (checkedList.isEmpty()) temp.add(-1)
    else temp.addAll(checkedList)

    temp.sortDescending()
    return temp.joinToString("")
}

fun morePlus(a: Int, b: Int): Int {
    val first = (a.toString() + b.toString()).toInt()
    val second = (b.toString() + a.toString()).toInt()
    return max(first, second)
}

fun solution(players: Array<String>, callings: Array<String>): Array<String> {
    val rankMap: HashMap<String, Int> = hashMapOf()

    //원래 플레이어들의 위치 값을 정리한다.
    for (i in players.indices) {
        rankMap[players[i]] = i
    }

    for (i in callings) {
        //계산 중인 배열에서 기존 위치 값 가져온다.
        val callRank = rankMap[i]

        //기존 위치 값 - 1 플레이어 찾는다.
        val front = players[callRank!! - 1]

        //두 개를 변경한다.
        players[callRank - 1] = i
        players[callRank] = front

        //이후 반복에서 사용할 rankMap에서도 변경한다.
        rankMap[i] = callRank - 1
        rankMap[front] = callRank
    }
    return players
}

fun findKin(seoul: Array<String>): String {
    var res = ""
    seoul.forEachIndexed { index, s ->
        if (s == "Kim") {
            res = "김서방은 ${index}에 있다"
            return@forEachIndexed
        }
    }
    return res
}

fun checkString(s: String): Boolean = (s.length == 4 || s.length == 6) && s.toIntOrNull() != null

fun waterMelon(n: Int): String {
    var res = ""
    repeat(n) {
        res += if (it % 2 == 0) {
            "수"
        } else {
            "박"
        }
    }
    return res
}

fun solution(s: String): Int {
    return s.toInt()
}


internal object Solution {
    @JvmStatic
    fun main(args: Array<String>) {
        val sc = Scanner(System.`in`)
        val a = sc.nextInt()
        val b = sc.nextInt()
        val star = "*"
        for (i in 0 until b) {
            for (j in 0 until a) {
                print(star)
            }
            println()
        }
    }
}

fun solution(x: Int, n: Int): LongArray {
    val res = mutableListOf<Long>()
    var temp = 0
    repeat(n) {
        temp += x
        res.add(temp.toLong())
    }
    return res.toLongArray()
}

fun weirdString(s: String): String {
    val splitString = s.split(' ')
    var answer = arrayOf<String>()

    splitString.forEach { s ->
        var string = ""
        s.forEachIndexed { index, c ->
            string += when (index % 2) {
                0 -> c.uppercaseChar()
                else -> c.lowercaseChar()
            }
        }
        answer += string
    }

    //배열 요소를 공백으로 구분하여 String으로 합친다.
    return answer.joinToString(' '.toString())
}

fun avr(arr: IntArray): Double = arr.average()

fun price(price: Int, money: Int, count: Int): Long {
    var temp = 0
    repeat(count) {
        temp += price + price * it
    }
    return if (temp <= money) return 0 else (temp - money).toLong()
}

fun solution(absolutes: IntArray, signs: BooleanArray): Int {
    val temp = mutableListOf<Int>()
    signs.forEachIndexed { index, value ->
        if (!value) temp.add(-absolutes[index])
        else temp.add(absolutes[index])
    }
    return temp.sum()
}

fun minSquare(sizes: Array<IntArray>): Int {
    val areas = mutableListOf<Int>()
    val widths = mutableListOf<Int>()
    val heights = mutableListOf<Int>()

    sizes.forEach {
        areas.add(it[0] * it[1])
        widths.add(it[0])
        heights.add(it[1])
    }
    val maxWidth = widths.maxOf { it }
    val maxAreas = areas.maxOf { it }
    heights.sortDescending()
    var res = 0

    heights.forEach {
        val temp = maxWidth * it
        if (temp > maxAreas) res = temp
        else return@forEach
    }
    return res

}

fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    val ones = cards1.toMutableList()
    val seconds = cards2.toMutableList()
    val temp = mutableListOf<String>()
    for (w in goal) {
        if (ones.isNotEmpty() && w == ones.firstOrNull()) {
            ones.removeFirst()
            temp.add(w)
            continue
        }
        if (seconds.isNotEmpty() && w == seconds.firstOrNull()) {
            seconds.removeFirst()
            temp.add(w)
            continue
        }
        return "No"
    }
    return if (temp.joinToString() == goal.joinToString()) "Yes" else "No"
}

fun solution(num: Int): Int {
    var num = num
    var answer = 0
    while (num != 1 && answer != 500) {
        if (num % 2 == 0) {
            num /= 2
        } else if (num % 2 == 1) {
            num = num * 3 + 1
        }
        answer++
    }
    if (answer == 500) {
        answer = -1
    }
    return answer
}

fun tests(x: Int): Boolean {
    var sum = 0
    var a = x

    while (a >= 1) {
        sum += a % 10
        a /= 10
    }
    return x % sum == 0
}

fun solution(k: Int, m: Int, score: IntArray): Int {
    score.sortDescending()
    val list = score.toMutableList().chunked(m)
    val size = mutableListOf<Int>()
    list.forEach { size.add(it.size) }
    val minValues = mutableListOf<Int>()
    list.forEach { minValues.add(it.min()) }

    val map = mutableMapOf<List<Int>, Int>()
    var tempSize = 0
    list.forEach {
        if (tempSize != it.size) {
            map[it] = it.size
            tempSize = it.size
        }
    }

    repeat(list.size) {

    }
    var answer: Int = 0
    return answer
}

fun solution(s: String, skip: String, index: Int): String {
    val texts = ('a'..'z').filter { it !in skip } //제외 대상 문자열 정리
    return s.map { texts[(texts.indexOf(it) + index) % texts.size] }.joinToString()
}

fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray>? {
    val answer = Array(arr1.size) { IntArray(arr1[0].size) }
    for (i in arr1.indices) {
        for (j in arr1[i].indices) {
            answer[i][j] = arr1[i][j] + arr2[i][j]
        }
    }
    return answer
}

fun solution(answers: IntArray): IntArray {
    val userAnswers = arrayOf(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
    )
    val correctCnt = intArrayOf(0, 0, 0)
    val res = mutableListOf<Int>()

    for ((index, value) in answers.withIndex()) {
        if (value == userAnswers[0][index % userAnswers[0].size]) {
            correctCnt[0] += 1
        }
        if (value == userAnswers[1][index % userAnswers[1].size]) {
            correctCnt[1] += 1
        }
        if (value == userAnswers[2][index % userAnswers[2].size]) {
            correctCnt[2] += 1
        }
    }

    for ((idx, score) in correctCnt.withIndex()) {
        if (score == correctCnt.maxOrNull()) {
            res.add(idx + 1)
        }
    }
    return res.toIntArray()
}

fun behindN(my_string: String, n: Int): String {
    val length = my_string.toCharArray().size
    val check = length - n
    var res = ""

    my_string.toCharArray().forEachIndexed { index, c ->
        if (index >= check) res += c
    }

    return res
}

fun todoList(todo_list: Array<String>, finished: Array<Boolean>): Array<String> {
    val todoMap = hashMapOf<String, Boolean>()

    repeat(todo_list.size) {
        todoMap[todo_list[it]] = finished[it]
    }

    val res = arrayListOf<String>()
    todoMap.forEach { (s, b) ->
        if (!b) res.add(s)
    }
    return res.toTypedArray()
}

fun nearOneFind(arr: Array<Int>, idx: Int): Int {
    arr.forEachIndexed { index, i ->
        if (index > idx - 1 && i == 1) return index
    }
    return -1
}

fun makeNumberOne(num_list: IntArray): Int {
    var answer = 0
    for (num in num_list) {
        var n = num
        var cnt = 0
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2
            } else {
                n -= 1
                n /= 2
            }
            cnt++
        }
        answer += cnt
    }
    return answer
}

fun secondsArea(arr: IntArray): IntArray {
    val res = mutableListOf<Int>()
    when (arr.count { it == 2 }) {
        0 -> {
            res.add(-1)
        }
        1 -> {
            res.add(2)
        }
        else -> {
            var cnt = 0
            var stId = 0
            var endId = 0
            arr.forEachIndexed { index, i ->
                if (i == 2) {
                    cnt++
                    if (cnt == 1) {
                        stId = index
                    } else {
                        endId = index
                    }
                }
            }
            return arr.copyOfRange(stId, endId + 1)
        }
    }
    return res.toIntArray()
}

fun countDown(start: Int, end: Int): IntArray {
    val res = mutableListOf<Int>()
    for (i in start downTo end) {
        res.add(i)
    }
    return res.toIntArray()
}

fun deleteAd(strArr: Array<String>): MutableList<String> {
    val res = mutableListOf<String>()
    strArr.forEach {
        if (!it.contains("ad")) res.add(it)
    }
    return res
}

fun setDefaultDpTextSize(defaultSize: Float, vararg items: TextView) {
    items.forEach { it.setTextSize(TypedValue.COMPLEX_UNIT_DIP, defaultSize) }
}

fun leftRight(str_list: Array<String>): Array<String> {
    var left = -1
    var right = -1
    str_list.forEachIndexed { index, s ->
        if (s == "l") left = index
        if (s == "r") right = index
    }

    @Suppress("KotlinConstantConditions")
    if (left == -1 && right == -1) {
        return emptyArray()
    } else if (left != -1) {
        return str_list.sliceArray(0 until left)
    } else if (right != -1) {
        return str_list.sliceArray(right + 1..str_list.lastIndex)
    } else {
        if (left < right) {
            return str_list.sliceArray(0 until left)
        } else if (right < left) {
            return str_list.sliceArray(right + 1..str_list.lastIndex)
        }
    }
    return emptyArray()
}

//배열에서 대소문자 변환
fun changeCase(strArr: Array<String>): ArrayList<String> {
    val res = arrayListOf<String>()
    strArr.forEachIndexed { index, s ->
        if (index % 2 == 0) {
            res.add(s.uppercase())
        } else {
            res.add(s.lowercase())
        }
    }
    return res
}

fun turnStr(args: Array<String>?) {
    val sc = Scanner(System.`in`)
    val a: String = sc.next()
    val list = a.split("".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray() // 한 문자 씩 잘라낸다.
    for (i in list.indices) {
        println(list[i])
    }
}

fun personalInformationCollectionValidityPeriod(
    today: String,
    terms: Array<String>,
    privacies: Array<String>
): IntArray {
    val calArr = mutableListOf<String>()
    val result = arrayListOf<Int>()

    privacies.forEach { privacy ->
        val date = privacy.split(" ").first()
        val name = privacy.split(" ")[1]
        terms.forEach { term ->
            val termArr = term.split(" ")
            val termName = termArr.first()
            val termSize = termArr[1]

            if (name == termName) {
                val dateArr = date.split(".")
                var year = dateArr.first().toInt()
                var month = dateArr[1].toInt()
                val day = dateArr[2].toInt()

                month += termSize.toInt()
                year += month / 12
                month %= 12
                val calDateString = "$year.$month.$day $termName"
                calArr.add(calDateString)
            }
        }
    }

    val todayArr = today.split(".")
    val todayYear = todayArr[0].toInt()
    val todayMonth = todayArr[1].toInt()
    val todayDate = todayArr[2].toInt()

    calArr.forEachIndexed { index, s ->
        val year = s.split(" ")[0].split(".")[0].toInt()
        val month = s.split(" ")[0].split(".")[1].toInt()
        val date = s.split(" ")[0].split(".")[2].toInt()

        if (year < todayYear) {
            result.add(index + 1)
        } else if (year == todayYear && month < todayMonth) {
            result.add(index + 1)
        } else if (year == todayYear && month == todayMonth && date < todayDate) {
            result.add(index + 1)
        }
    }

    return result.toIntArray()
}

fun checkPrefix(my_string: String, is_prefix: String): Int {
    val prefixList = mutableListOf<String>()
    var stackStr = ""
    my_string.forEach {
        stackStr += it.toString()
        prefixList.add(stackStr)
    }
    return if (is_prefix in prefixList) 1 else 0
}

fun carveList(arr: IntArray, query: IntArray): IntArray {
    var answer: IntArray = arr

    for (i in query.indices) {
        val firstIdx = 0
        val lastIdx = answer.size - 1
        answer = if (i % 2 == 0) {
            answer.slice(firstIdx..query[i]).toIntArray()
        } else {
            answer.slice(query[i]..lastIdx).toIntArray()
        }
    }
    return answer
}

fun multiplyString(my_string: String, k: Int): String {
    var answer = ""
    repeat(k) {
        answer += my_string
    }
    return answer
}

fun connectNumber(num_list: IntArray): Int {
    var evenStr = ""
    var oddStr = ""
    num_list.forEach {
        if (it % 2 == 0) {
            evenStr += it.toString()
        } else {
            oddStr += it.toString()
        }
    }
    return evenStr.toInt() + oddStr.toInt()
}

fun mixedString(str1: String, str2: String): String {
    var res = ""
    repeat(str1.length) {
        res += str1[it]
        res += str2[it]
    }
    return res
}

fun listToString(arr: Array<String>): String = join("", arr.toList())

fun processedCode(code: String): String {
    var mode = 0
    var ret = ""
    code.forEachIndexed { index, c ->
        if (c.toString() == "1") {
            mode = if (mode == 0) 1 else 0
        }
        if (mode == 0) {
            if (index % 2 == 0) ret += c.toString()
        } else {
            if (index % 2 != 0) ret += c.toString()
        }
    }
    return ret
}

fun newId(new_id: String): String {
    return new_id.lowercase()
        .filter { it.isLowerCase() || it.isDigit() || it == '-' || it == '_' || it == '.' }
        .replace("[.]*[.]".toRegex(), ".")
        .removePrefix(".").removeSuffix(".")
        .let { it.ifEmpty { "a" } }
        .let { if (it.length >= 16) it.substring(0, 16).removeSuffix(".") else it }
        .let {
            if (it.length <= 2) {
                val sb = java.lang.StringBuilder(it)
                while (sb.length != 3) sb.append(it.last())
                sb.toString()
            } else it
        }
}

fun chunkedArray(num_list: IntArray, n: Int): Array<IntArray> {
    val list = num_list.toMutableList()
    val res = list.chunked(n)
    val result = mutableListOf<IntArray>()
    res.forEach {
        result.add(it.toIntArray())
    }
    return result.toTypedArray()
}

fun solution(denum1: Int, num1: Int, denum2: Int, num2: Int): IntArray {
    val denum3 = (num1 * denum2) + (num2 * denum1)
    val num3 = num1 * num2
    var min = 1 // 최소 공배수

    for (i in min..denum3) {
        if (denum3 % i == 0 && num3 % i == 0) {
            min = i
        }
    }
    return intArrayOf(denum3 / min, num3 / min)
}

fun overWrite(my_string: String, overwrite_string: String, s: Int): String {
    return my_string.substring(
        0,
        s
    ) + overwrite_string + my_string.substring(overwrite_string.length + s)
}