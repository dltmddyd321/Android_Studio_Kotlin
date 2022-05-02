package com.example.postservice.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShippingCompany(
    @PrimaryKey
    val code : String,
    val name : String
)