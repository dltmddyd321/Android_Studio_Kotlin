package com.example.programmerstest

import android.util.TypedValue
import android.widget.TextView
import java.lang.String.join
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

fun main() {
    println(listToString(arrayOf("a", "b", "c")))
}

fun morePlus(a: Int, b: Int): Int {
    val first = (a.toString() + b.toString()).toInt()
    val second = (b.toString() + a.toString()).toInt()
    return max(first, second)
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