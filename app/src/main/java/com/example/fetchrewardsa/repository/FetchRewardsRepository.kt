package com.example.fetchrewardsa.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.database.FetchRewardsDao
import com.example.fetchrewardsa.mapping.FetchItemMapper
import com.example.fetchrewardsa.network.FetchRewardsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FetchRewardsRepository @Inject constructor(
    private val api: FetchRewardsApi,
    private val fetchRewardsDao: FetchRewardsDao
) {

    val feeds: LiveData<List<FetchItemEntity>>
        get() = fetchRewardsDao.getAllInfo()

    //Using the resource file to handle the network calls
    //and sending them into the local database
    suspend fun fetchInfo(): Resource<List<FetchItemEntity>?>{
    return try {
        val request = api.getAllFetchRewardsInfo()
        if (!request.isSuccessful){
            return  Resource.Error(message = request.message())
        }

        val fetchItems = request.body()?.filter {
            !it.name.isNullOrEmpty()
        }?.map {
            FetchItemMapper.buildFrom(it)
        }

        fetchItems?.let {
            fetchRewardsDao.insertAll(*it.toTypedArray())
            Resource.Success(data = it)
        } ?: Resource.Error(message = "Empty Response")
    } catch (e: Exception){
        Resource.Error(message = e.message ?: "Unknown Error")
    }

    }

    //For the details of an individual item
    fun getItemById(itemId: Int?): Flow<FetchItemEntity?> {
        return itemId?.let {
            fetchRewardsDao.getInfoById(it)
        } ?: flowOf(null)
    }

}