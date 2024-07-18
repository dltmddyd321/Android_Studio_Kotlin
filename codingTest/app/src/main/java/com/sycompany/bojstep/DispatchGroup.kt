package com.sycompany.bojstep

class DispatchGroup {

    private var count = 0
    private var runnable: (() -> Unit)? = null

    init {
        count = 0
    }

    @Synchronized
    fun enter() {
        count++
    }

    @Synchronized
    fun leave() {
        count--
        notifyGroup()
    }

    fun notify(r: () -> Unit) {
        runnable = r
        notifyGroup()
    }

    private fun notifyGroup() {
        if (count <= 0) {
            runnable?.let { it() }
        }
    }
}

fun main() {
    val arr = arrayOf("..", "#.")
    println(solution(arr).joinToString(""))
}

fun solution(wallpaper: Array<String>): IntArray {
    data class Position(
        val x: Int,
        val y: Int
    )

    val list = mutableListOf<Position>()

    wallpaper.forEachIndexed { indexX, xValue ->
        xValue.forEachIndexed { indexY, yValue ->
            if (yValue == '#') list.add(Position(indexX, indexY))
        }
    }
    val minXPos = list.minOf { it.x }
    val minYPos = list.minOf { it.y }
    val startPos = Position(minXPos, minYPos)

    val maxXPos = list.maxOf { it.x }
    val maxYPos = list.maxOf { it.y }
    val endPos = Position(maxXPos + 1, maxYPos + 1)

    val res = mutableListOf<Int>()
    res.add(startPos.x)
    res.add(startPos.y)
    res.add(endPos.x)
    res.add(endPos.y)
    return res.toIntArray()
}