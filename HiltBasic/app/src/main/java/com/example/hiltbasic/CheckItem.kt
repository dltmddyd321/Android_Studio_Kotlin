package com.example.hiltbasic

import com.google.gson.annotations.SerializedName

data class InformationResult(
    val responseStatus: String,
    val errorNumber: Int,
    val message: String? = null,
    val data: ArrayList<InformationData>
)

data class InformationData(
    val id: Int,
    val functionName: String,
    val settingValueType: String,
    val settingValue: String
)