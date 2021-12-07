package com.example.realmtodo

open class Person : RealmObject() {
    // All fields are by default persisted.
    var age: Int = 0

    // Adding an index makes queries execute faster on that field.
    @Index
    lateinit var name: String

    // Primary keys are optional, but it allows identifying a specific object
    // when Realm writes are instructed to update if the object already exists in the Realm
    @PrimaryKey
    var id: Long = 0L

    // Other objects in a one-to-one relation must also implement RealmModel, or extend RealmObject
    var dog: Dog? = null

    // One-to-many relations is simply a RealmList of the objects which also implements RealmModel
    var cats: RealmList<Cat>? = null

    // It is also possible to have list of primitive types (long, String, Date, byte[], etc.)
    var phoneNumber: RealmList<String>? = null

    // You can instruct Realm to ignore a field and not persist it.
    @Ignore
    var tempReference: Int? = 0
    // Let your IDE generate getters and setters for you!
    // Or if you like you can even have public fields and no accessors! See Dog.java and Cat.java

    // 이하 getter & setter
}