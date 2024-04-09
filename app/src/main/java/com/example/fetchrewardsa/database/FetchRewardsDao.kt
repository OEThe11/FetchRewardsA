package com.example.fetchrewardsa.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FetchRewardsDao {

    @Query("SELECT * FROM fetch_entity ORDER BY listId ASC")
    fun getAllInfo(): LiveData<List<FetchItemEntity>>

    @Query("SELECT * FROM fetch_entity WHERE id = :itemId")
    fun getInfoById(itemId: Int): Flow<FetchItemEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg info: FetchItemEntity)
}