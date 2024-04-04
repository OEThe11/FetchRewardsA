package com.example.fetchrewardsa.screens.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.repository.FetchRewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FetchRewardsRepository
): ViewModel(){

    val fetchInfoRewards = repository.feeds
    val isLoading = mutableStateOf(false)

    init {
        getFetchList()
    }

    private fun getFetchList() {
        viewModelScope.launch  {
            isLoading.value = true
            delay(2000)
            try {
                if (repository.fetchInfo().data?.isNotEmpty() == true){
                    isLoading.value = false
                }
            }catch (e: Exception){
                Log.e(TAG, e.message, e.cause )
            }
        }

    }


}