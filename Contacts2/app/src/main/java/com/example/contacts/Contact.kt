package com.example.contacts

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    var id : Long?,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: Char
) {
    constructor() : this(null, "", "", '\u0000')
}