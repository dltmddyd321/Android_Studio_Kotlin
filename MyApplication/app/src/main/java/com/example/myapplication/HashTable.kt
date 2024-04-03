package com.example.myapplication

import java.util.Deque
import java.util.LinkedList

class HashTable {
    constructor()

    fun main() {
        val studentInfo = hashMapOf<Int, String>()
        studentInfo[2024390] = "카리나"
        studentInfo[2024392] = "아이유"
        studentInfo[2024393] = "장원영"
        studentInfo[2024401] = "차은우"

        if (2024390 in studentInfo) {
            println("학생이 존재합니다")
        } else {
            println("학생이 존재하지 않습니다")
        }

        studentInfo.forEach { (i, s) ->
            print("$i $s")
        }

        studentInfo.keys.forEach {
            print(it)
        }

        print(studentInfo[2024390])
    }

    fun dequeBasic() {
        //덱 생성
        val myDeque: Deque<Int> = LinkedList()
        val second = ArrayDeque<Int>()

        myDeque.addFirst(1)
        myDeque.addLast(2)

        myDeque.pollFirst()
        myDeque.pollLast()

        second.removeFirst()
        second.removeLast()

        val size = myDeque.size

        print(myDeque)
    }
}