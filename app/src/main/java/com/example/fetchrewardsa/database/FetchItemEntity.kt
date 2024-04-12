package com.example.fetchrewardsa.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fetch_entity")
data class FetchItemEntity(
    @PrimaryKey
    val id: Int,
    val listId: Int,
    val name: String
)
