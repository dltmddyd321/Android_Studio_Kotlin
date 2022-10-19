package com.sycompany.realmbasicschema

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User (
    @PrimaryKey
    var id: Int? = 0,
    var home: String? = null,
    var name: String? = null,
    var age: Int? = 0,
) : RealmObject()

open class Pet (
    @PrimaryKey
    var id: Int? = 0,
    var value: Int? = null,
    var name: String? = null,
    var age: Int? = 0,
) : RealmObject()