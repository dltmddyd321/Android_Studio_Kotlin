package com.example.coroutinemodel

class DelegationTest(private val text: String): TestInterface {
    override fun checkDelegation() {
        println("결과는 다음과 같다. -> $text")
    }
}

val owner = DelegationTest("Delegation Success!")
class Son: TestInterface by owner
class Daughter: TestInterface by owner

fun main() {
    DelegationTest("Very Good!").checkDelegation()
    Son().checkDelegation()
    Daughter().checkDelegation()
}

