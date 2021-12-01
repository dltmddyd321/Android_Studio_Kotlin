package com.example.kotlinbasic

fun main() {
    println(sqaure(12))
    println(nameAge("LeeSY", 24))

    val a = "joy said "
    val b = "mac said "
    println(a.pizzaIsGreat())
    println(b.pizzaIsGreat())

    println(extendStr("Mike", 26))
    println(calculateGrade(92))

    val lambda = {num: Double ->
        num == 4.5234
    }

    println(invokeLambda(lambda))
    println(invokeLambda({it > 1.654}))

    //마지막 파라미터가 람다식일때 나오는 형태
    println(invokeLambda { it > 3.222 })

}

//Int 삽입 후 Int 반환 Lambda
val sqaure : (Int) -> (Int) = { number ->
    number * number
}

val nameAge = { name : String, age:Int ->
    "My name is $name, I'm $age"
}

//확장 함수
val pizzaIsGreat : String.() -> String = {
    this + "Pizza is Good!"
}

fun extendStr(name: String, age: Int) : String {
    val introduceMySelf : String.(Int) -> String = {
        "I am $this and $it years old."
    }
    return name.introduceMySelf(age)
}

val calculateGrade : (Int) -> String = {
    when(it) {
        in 0..40 -> "Fail"
        in 41..70 -> "PASS"
        in 71..100 -> "SSS!"
        else -> "Nothing"
    }
}

//Double 받고, Boolean 반환하는 Lambda
//lambda를 통해 반환되는 Boolean이 invokeLambda의 Boolean으로 Return
fun invokeLambda(lambda : (Double) -> Boolean) : Boolean {
    return lambda(5.543)
}

