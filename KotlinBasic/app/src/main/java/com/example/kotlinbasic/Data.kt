package com.example.kotlinbasic

data class Ticket(
    val companyName: String,
    val name: String,
    var date: String,
    var seatNumber: Int
)

fun main() {
    val ticketA = Ticket("Korea Air", "이승용", "2021-05-14", 16)
}

