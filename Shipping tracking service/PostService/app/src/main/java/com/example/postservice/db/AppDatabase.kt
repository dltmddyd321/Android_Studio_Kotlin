package com.example.postservice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.postservice.entity.TrackingItem

@Database(
    entities = [TrackingItem::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun trackingItemDao() : TrackingDao

    companion object {
        private const val DATABASE_NAME = "tracking.db"

        fun build(context: Context) : AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }
}