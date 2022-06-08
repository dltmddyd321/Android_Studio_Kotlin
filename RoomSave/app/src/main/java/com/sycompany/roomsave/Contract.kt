package com.sycompany.roomsave

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_contracts")
data class Contract (
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String,
    var tel: String
)