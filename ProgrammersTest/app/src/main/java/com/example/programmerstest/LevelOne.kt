package com.example.programmerstest

import kotlin.math.max

fun main() {
    val b = arrayOf(1, 1, 1, 1, 0)
    val a = intArrayOf(1, 2, 1, 2, 1, 10, 2, 1)
    secondsArea(a).forEach {
        println(it)
    }
    println(secondsArea(a))
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
            if (n %2 == 0) {
                n /= 2
            } else {
                n -= 1
                n /= 2
            }
            cnt++
        }
        answer+= cnt
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
                    cnt ++
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