package com.example.postservice.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postservice.entity.TrackingItem

@Dao
interface TrackingDao {

    @Query("SELECT * FROM TrackingItem")
    suspend fun getAll() : List<TrackingItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item : TrackingItem)
}