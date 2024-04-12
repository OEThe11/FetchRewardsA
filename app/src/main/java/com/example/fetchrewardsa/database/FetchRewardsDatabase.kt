package com.example.fetchrewardsa.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FetchItemEntity::class], version = 1)
abstract class FetchRewardsDatabase : RoomDatabase() {
    abstract fun fetchRewardsDao(): FetchRewardsDao
}