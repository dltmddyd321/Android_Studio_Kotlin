package com.example.programmerstest

import java.util.*
import kotlin.collections.ArrayList

class Algorithm {

    var arrNum = arrayListOf(5, 2, 3, 4, 1)

    fun bubbleSort() { //O(n제곱)
        var temp: Int
        for (i in arrNum) { //리스트 크기 만큼 반복
            for (j in 0 until arrNum.size - 1) { //마지막 인덱스 전까지 반복
                if (arrNum[j] > arrNum[j + 1]) {
                    temp = arrNum[j]
                    arrNum[j] = arrNum[j + 1]
                    arrNum[j + 1] = temp
                }
            }
        }
    }

    fun selectionSort(): ArrayList<Int> { //O(n제곱)
        var min: Int

        fun changePos(numList: ArrayList<Int>, min: Int, i: Int) {
            val temp: Int = numList[min]
            numList[min] = numList[i]
            numList[i] = temp
        }

        for (i in arrNum.indices) {
            min = i
            for (j in i + 1 until arrNum.size) {
                if (arrNum[j] < arrNum[min]) min = j
            }
            changePos(arrNum, min, i)
        }

        return arrNum
    }

    fun insertSort() {
        var last: Int
        var temp: Int

        //정렬 검사가 필요한 1번 인덱스부터 시작
        for (i in 1 until arrNum.size) {
            temp = arrNum[i]
            last = i

            while ((last > 0) && (arrNum[last - 1] > temp)) {
                arrNum[last] = arrNum[last - 1]
                last--
            }
            arrNum[last] = temp
        }
    }

    /*
    - 삽입: Insertion O(1)
    - 삭제: Deletion O(1)(pop) / O(N)(remove)
    - 검색: Search O(N)
     */
    fun stack() {
        val s = Stack<Int>()

        s.push(1) // 객체를 스택에 추가
        s.push(3)

        s.peek() // 맨 위의 객체 반환 (비어 있는 상태이면 EmptyStackException 발생)
        println(s)

        s.pop() // 맨 위의 객체 삭제하고 반환 (비어 있는 상태이면 EmptyStackException 발생)
        println(s)
    }

    /*
    - 삽입: Insertion O(1)(enqueue)
    - 삭제: Deletion O(1)(dequeue) / O(N)(remove)
    - 검색: Search O(N)
     */
    fun queue() {
        val q : Queue<Int> = LinkedList() // 큐로 선언하고 LinkedList 로 할당
        q.add(1)    // 객체를 큐에 추가 (큐가 가득찬 상태이면 illegalStateException 발생)
        q.offer(3)  // 객체를 큐에 추가 (큐가 가득찬 상태이면 false 반환)
        println(q)

        println(q.element())    // 맨 앞 객체 리턴 (큐가 비어있는 상태이면 NoSuchElementException 발생)
        println(q.elementAt(1)) // 인덱스 값의 객체 리턴
        println(q.peek())   // 맨 앞 객체 리턴 (큐가 비어있는 상태이면 false 반환)

        q.remove()  // 삭제하면서 객체 반환 (큐가 비어있는 상태이면 NoSuchElementException 발생)
        val tmp = q.poll()  // 삭제하면서 객체 반환 (큐가 비어있는 상태이면 false 반환)
        println(q)
        println("두번째 삭제한 객체 : $tmp")
    }

    fun priorityQueue() { //우선 순위 큐
        val q = PriorityQueue<Int>() // 오름차순
        val q2 = PriorityQueue<Int>(Collections.reverseOrder()) // 내림차순

        q.addAll(listOf(1,5,2,4,3))
        q2.addAll(listOf(1,5,2,4,3))

        // 우선 순위가 높은 것부터 반환
        while(!q.isEmpty()) print("${q.poll()} ")
        println()
        while(!q2.isEmpty()) print("${q2.poll()} ")
    }
}