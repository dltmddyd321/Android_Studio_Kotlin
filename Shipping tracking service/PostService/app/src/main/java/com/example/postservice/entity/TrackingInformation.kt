package com.example.postservice.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackingInformation(
    @SerializedName("adUrl")
    val adUrl: String? = null,
    @SerializedName("complete")
    val complete: Boolean? = null,
    @SerializedName("completeYN")
    val completeYN: String? = null,
    @SerializedName("estimate")
    val estimate: String? = null,
    @SerializedName("firstDetail")
    val firstDetail: TrackingDetail? = null,
    @SerializedName("invoiceNo")
    val invoiceNo: String? = null,
    @SerializedName("itemImage")
    val itemImage: String? = null,
    @SerializedName("itemName")
    val itemName: String? = null,
    @SerializedName("lastDetail")
    val lastDetail: TrackingDetail? = null,
    @SerializedName("lastStateDetail")
    val lastStateDetail: TrackingDetail? = null,
    @SerializedName("level")
    val level: Level? = null,
    @SerializedName("orderNumber")
    val orderNumber: String? = null,
    @SerializedName("productInfo")
    val productInfo: String? = null,
    @SerializedName("receiverAddr")
    val receiverAddr: String? = null,
    @SerializedName("receiverName")
    val receiverName: String? = null,
    @SerializedName("recipient")
    val recipient: String? = null,
    @SerializedName("result")
    val result: String? = null,
    @SerializedName("senderName")
    val senderName: String? = null,
    @SerializedName("trackingDetails")
    val trackingDetails: List<TrackingDetail>? = null,
    @SerializedName("zipCode")
    val zipCode: String? = null,

    @SerializedName("msg")
    val errorMessage: String? = null
) : Parcelable