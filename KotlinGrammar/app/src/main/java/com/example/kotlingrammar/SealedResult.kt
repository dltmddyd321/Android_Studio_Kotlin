package com.example.kotlingrammar

sealed class SealedResult { //Sealed 클래스 선언 형식
    open class Success(val message: String): SealedResult()
    class Error(val code: Int, val message: String): SealedResult()
}

//Sealed 클래스 상속은 같은 파일 내에서만 가능
class Status : SealedResult()
class Inside : SealedResult.Success("Status")

fun main() {
    val result = SealedResult.Success("Good")
    val msg = eval(result)
    println(msg)

    val result2 = Inside()
    val msg2 = eval(result2)
    println(msg2)

    val result3 = Status()
    val msg3 = eval(result3)
    println(msg3)

    val result4 = SealedResult.Error(1, "hello")
    val msg4 = eval(result4)
    println(msg4)
}

fun eval(result: SealedResult): String = when(result){
    is Status -> "Status 객체"
    is SealedResult.Success -> "[Success] ${result.message}"
    is SealedResult.Error -> "[Error] ${result.message}"
}