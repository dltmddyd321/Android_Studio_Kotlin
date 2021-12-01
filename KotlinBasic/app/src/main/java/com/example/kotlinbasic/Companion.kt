package com.example.kotlinbasic

//외부에서는 해당 객체 생성 못하도록 private constructor
class Book private constructor(val id: Int, val name: String) {

    //JAVA의 Static 역할
   companion object BookFactory : IdProvider{

        val myBook = "New Book"

        fun create() = Book(getId(), myBook)

        override fun getId(): Int {
            return 1234
        }
    }
}

interface IdProvider {
    fun getId() : Int
}

fun main() {
    val book = Book.create()

    val bookId = Book.getId()
    println("${book.id}, ${book.name}")
}