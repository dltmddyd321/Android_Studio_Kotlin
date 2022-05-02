package com.example.postservice.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackingDetail(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("level")
    val level: Int? = null,
    @SerializedName("manName")
    val manName: String? = null,
    @SerializedName("manPic")
    val manPic: String? = null,
    @SerializedName("remark")
    val remark: String? = null,
    @SerializedName("telno")
    val telno: String? = null,
    @SerializedName("telno2")
    val telno2: String? = null,
    @SerializedName("time")
    val time: Long? = null,
    @SerializedName("timeString")
    val timeString: String? = null,
    @SerializedName("where")
    val `where`: String? = null
) : Parcelable
