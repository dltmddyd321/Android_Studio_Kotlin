package com.example.postservice.repository

import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem

interface TrackingItemRepository {

    suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>>
}