package com.example.fetchrewardsa.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FetchRewardsDao {

    @Query("SELECT * FROM fetch_entity ORDER BY listId ASC")
    fun getAllInfo(): LiveData<List<FetchItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg info: FetchItemEntity)
}