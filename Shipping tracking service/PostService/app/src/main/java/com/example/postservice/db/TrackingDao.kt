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

    //이미 저장되어 있다면 새로 추가하지 않는다.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item : TrackingItem)
}