package com.example.postservice.entity

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["invoice", "code"])
data class TrackingItem(
    val invoice : String,
    //임베디드 키워드를 통해 테이블 내부 직접 접근 가능
    @Embedded val company: ShippingCompany
)