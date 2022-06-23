package com.sycompany.moshibasic

import com.squareup.moshi.Json

data class Advice(
    @Json(name = "slip") val slip : Slip
)

data class Slip(
    val id: Int,
    val advice: String
)