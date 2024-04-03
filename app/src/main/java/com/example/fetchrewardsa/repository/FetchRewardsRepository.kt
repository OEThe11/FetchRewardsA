package com.example.fetchrewardsa.repository

import androidx.lifecycle.LiveData
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.database.FetchRewardsDao
import com.example.fetchrewardsa.mapping.FetchItemMapper
import com.example.fetchrewardsa.network.FetchRewardsApi
import javax.inject.Inject

class FetchRewardsRepository @Inject constructor(
    private val api: FetchRewardsApi,
    private val fetchRewardsDao: FetchRewardsDao
) {

    val feeds: LiveData<List<FetchItemEntity>>
        get() = fetchRewardsDao.getAllInfo()

    //The Filtering of null and Empty Strings happens here
    suspend fun fetchInfo(): List<FetchItemEntity>? {
        val request = api.getAllFetchRewardsInfo()
        if (request.isSuccessful){
            val fetchItems = request.body()!!.filter {
                !it.name.isNullOrEmpty()
            }.map {
               FetchItemMapper.buildFrom(it)
            }
            fetchRewardsDao.insertAll(*fetchItems.toTypedArray())
            return fetchItems
        }
        return null
    }

}