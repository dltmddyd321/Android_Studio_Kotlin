package com.example.unsplash.models


import com.google.gson.annotations.SerializedName

data class CurrentUserCollection(
    @SerializedName("cover_photo")
    val coverPhoto: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_collected_at")
    val lastCollectedAt: String?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user")
    val user: Any?
)