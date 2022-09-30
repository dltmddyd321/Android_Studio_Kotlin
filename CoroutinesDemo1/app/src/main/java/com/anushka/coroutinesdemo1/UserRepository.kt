package com.anushka.coroutinesdemo1

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers(): List<User> {
        delay(1000)
        val users: List<User> = listOf(
            User(1, "Lee"),
            User(2, "Taro"),
            User(3, "Ann"),
            User(4, "Luna")
        )
        return users
    }
}