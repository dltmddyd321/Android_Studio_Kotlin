package com.example.realmtodo

open class Cat : RealmObject() {

    var name : String? = null

    // You can define inverse relationships
    @LinkingObjects("cats")
    val owners: RealmResults<Person>? = null
}