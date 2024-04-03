package com.example.fetchrewardsa.mapping

import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.models.FetchGetResponseItem

object FetchItemMapper {

    fun buildFrom(response: FetchGetResponseItem): FetchItemEntity{
        return FetchItemEntity(
            id = response.id,
            listId = response.listId,
            name = response.name ?: ""
        )
    }
}