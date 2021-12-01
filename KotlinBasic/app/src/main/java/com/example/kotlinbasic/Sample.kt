package com.example.kotlinbasic

import java.security.acl.AclNotFoundException

fun main() {

//    val name = "LeeSY"
//    val age = 23
//
//    println("My name is ${name}, $age years old.")
//
//    checkNum(1)
//
//    forAndWhile()

    ignoreNulls("abcde")
}

//아무런 Return 형식이 없을 때 Unit 명시하거나 생략 가능
fun helloWorld() : Unit {
    println("Hello World!!")
}

fun addNum(a: Int, b: Int) : Int {
    return a+b
}

fun valVar() {
    //타입을 생략해도 무방(:type)
    val a: Int = 10
    var b: Int = 7
    var name = "LeeSY"
}

fun maxBy(a: Int, b: Int) : Int {
    return if(a > b) {
        a
    } else {
        b
    }
}

//값을 만들어내는 표현식
fun maxBy2(a: Int, b: Int)= if(a>b) a else b

//Statement
fun checkNum(score: Int) {
    when(score) {
        0 -> println("This is 0")
        1 -> println("This is 1")
        2 -> println("This is 2")
        else -> println("Nothing")
    }

    var b = when(score) {
        1 -> 1
        2 -> 2
        else -> 3
    }

    println("b : $b")

    when(score) {
        in 90..100 -> print("A")
        in 80..89 -> print("B")
        else -> println("PASS")
    }
}

// Expression VS Statement
//코틀린 대부분의 함수는 Expression

//List는 읽기만 가능하나, MutableList는 읽기, 쓰기 모두 가능
fun array() {
    //array와 list 초기화
    val array: Array<Int> = arrayOf(1,2,3)
    val list: List<Int> = listOf(1,2,3)

    val array2: Array<Any> = arrayOf(1,"43245",false)
    val list2: List<Any> = listOf(1,"43245",false)

    array[0] = 3
    var result = list[1]

    val arrayList = arrayListOf<Int>()
    arrayList.add(10)
    arrayList.add(20)
    arrayList[0] = 30
}

fun forAndWhile() {
    val students = arrayListOf("J", "B", "K")

    for( name in students) {
        println(name)
    }

    var sum : Int = 0
    var exSum : Int = 0
    var untilSum : Int = 0

    for(i in 1..10 step 2) {
        sum += i
    }

    for(j in 10 downTo 1) {
        exSum += j
    }

    //1부터 99까지 순회
    for(k in 1 until 100) {
        untilSum += k
    }
    println(sum)
    println(exSum)
    println(untilSum)

    var index = 0
    while (index < 10) {
        println("current index : $index")
        index ++
    }

    for((index, name) in students.withIndex()) {
        println("${index + 1}번째 학생 : $name")
    }
}

// Nullable / NonNull
fun nullCheck() {

    var name = "Jason"

    //Null 초기화가 필요하다면 아래와 같이 사용
    var nullName : String? = null

    var nameToUpper = name.toUpperCase()

    //null이면 null 반환, 값이 존재한다면 Method 실행
    var nullNameToUpper = nullName?.toUpperCase()

    val lastName : String? = "Albert"

    val fullName = name + " " + (lastName ?: "None")

    println(fullName)

}

fun ignoreNulls(str : String?) {
    //!!는 절대 Null이 아니라는 것을 의미 -> 하지만 NPE를 일으킬 확률이 높아 지양
    val mNotNull : String = str!!

    val upper = mNotNull.toUpperCase()

    println(upper)

    val email : String?= "dltmddyd321@naver.com"

    //자신의 Receiver 객체를 Lambda 식 내부로 옮겨 사용
    email?.let {
        println("My email is $email")
    }
}