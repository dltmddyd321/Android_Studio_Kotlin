package com.example.programmerstest

import kotlin.math.max

fun main() {
    val a = arrayOf("problemsolving", "practiceguitar", "swim", "studygraph")
    val b = arrayOf(true, false, true, false)
    todoList(a, b).forEach {
        println(it)
    }
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