package com.example.realmtodo

@RealmClass
open class Dog : RealmModel {
    var name: String? = null

    // You can define inverse relationships
    @LinkingObjects("dog")
    val owners: RealmResults<Person>? = null
}