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
}