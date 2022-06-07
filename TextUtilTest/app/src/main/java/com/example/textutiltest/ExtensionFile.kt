package com.example.textutiltest

import java.util.*

class Person(val name: String) {
    fun walk() = "person is walking"
    var nameList = mutableListOf<String>()
}

fun Person.walk() = "unknown function"
fun Person.nameToUpperCase() = this.name.uppercase(Locale.getDefault())

//Extension Property
var Person.lastMan: String
    get() = this.nameList.last()
    set(value) { this.nameList.add(value) }

fun main() {
    val person = Person("leesy")
    println(person.nameToUpperCase())

    //The member method takes precedence.
    println(person.walk())
}

/*
#Extension Function
1. Extended Target Class.Function Name()
2. If an extension function is defined in 'Any' type, it can be used in all objects.
3. In the case of the extension function, private and protected members that can only be used internally are not accessible.
4. The member method takes precedence.
 */