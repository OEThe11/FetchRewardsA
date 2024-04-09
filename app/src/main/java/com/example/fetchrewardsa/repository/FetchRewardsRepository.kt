package com.example.fetchrewardsa.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.database.FetchRewardsDao
import com.example.fetchrewardsa.mapping.FetchItemMapper
import com.example.fetchrewardsa.network.FetchRewardsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val TAG = "FetchRewardsRepository"

class FetchRewardsRepository @Inject constructor(
    private val api: FetchRewardsApi,
    private val fetchRewardsDao: FetchRewardsDao
) {
    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val feeds: StateFlow<List<FetchItemEntity>> = fetchRewardsDao.getAllInfo()
        .stateIn(
            scope = repositoryScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


    //Using the resource file to handle the network calls
    //and sending them into the local database
    suspend fun fetchInfo(): Resource<List<FetchItemEntity>> {
        return try {
            val response = api.getAllFetchRewardsInfo()
            Log.d(TAG, "fetchInfo: API response: $response")

            if (response.isSuccessful) {
                // Filter out items with null or empty names and convert them
                val fetchItems = response.body()?.filterNot {
                    it.name.isNullOrEmpty() }?.map { FetchItemMapper.buildFrom(it) }

                if (fetchItems.isNullOrEmpty()) {
                    Resource.Error("Empty Response")
                } else {
                    // Insert non-null items into the database
                    fetchRewardsDao.insertAll(*fetchItems.toTypedArray())
                    Resource.Success(fetchItems)
                }
            } else {
                Resource.Error("API Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.message ?: "Unknown Error"}")
        }
    }




    fun getById(id: Int): Flow<Resource<FetchItemEntity>> = flow {
        try {
            emit(Resource.Loading())
            val data = fetchRewardsDao.getInfoById(id).firstOrNull()
            if (data != null){
                emit(Resource.Success(data))
            }
            else{
                emit(Resource.Error("Data not found"))
            }
        } catch (e: Exception){
            emit(Resource.Error("An error occurred: ${e.message}"))
        }
    }
}