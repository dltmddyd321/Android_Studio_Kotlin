package com.example.programmerstest

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
}