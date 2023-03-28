package com.sycompany.bojstep

interface EventListener {
    fun onEvent(cnt: Int)
}

class Counter(var listener: EventListener) {
    fun count() {
        repeat(100) {
            if (it % 5 == 0) {
                listener.onEvent(it)
            }
        }
    }
}

class EventPrinter {
    fun start() {
        val counter = Counter(object : EventListener {
            override fun onEvent(cnt: Int) {
                print("${cnt}!!\n")
            }
        })
        counter.count()
    }
    //콜백 메서드를 구현
//    override fun onEvent(cnt: Int) {
//        print("${cnt}!!\n")
//    }

    //실제 동작 구현부를 구성
//    fun start() {
//        Counter(this).count()
//    }
}

fun main() {
    EventPrinter().start()
}

fun pizzaOne(n: Int) = if (n % 7 == 0) {
    n / 7
} else {
    n / 7 + 1
}

fun checking(angle: Int): Int {
    if (angle == 180) return 4
    return when (angle) {
        in 1..89 -> 1
        90 -> 2
        in 91..179 -> 3
        else -> 0
    }
}

fun hidePlus(my_string: String): Int {
    val a = my_string.split("")
    var res = 0
    for (i in a.indices) {
        if (a[i].toIntOrNull() != null) { //Int형 변환에 성공 시
            res += a[i].toInt()
        }
    }
    return res
}

fun emergencyCheck(emergency: IntArray) = emergency.map {
    //내림차순하여 인덱스 체크
    emergency.sortedDescending().indexOf(it) + 1
}.toIntArray()

//369 게임
fun three(order: Int) = order.toString().map { it.digitToInt() }
    .count { it != 0 && it % 3 == 0 }

fun mostFound(array: IntArray): Int {
    if (array.size == 1) return 1
    val checkList = mutableMapOf<Int, Int>()
    val arr = array.toList()
    val distinctList = arr.distinct()
    distinctList.forEach {
        checkList[it] = 0
    }

    arr.forEach { basic ->
        checkList.forEach {
            if (basic == it.key) checkList[it.key] = it.value + 1
        }
    }

    val sortMap = checkList.toList().sortedByDescending { it.second }.toMap() as MutableMap
    if (sortMap[0] == sortMap[1]) return -1
    return sortMap[0] ?: -1
}