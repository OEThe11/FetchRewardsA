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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FetchRewardsRepository
): ViewModel(){

    val fetchInfoRewards = repository.feeds

    private val _fetchStatus = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val fetchStatus: StateFlow<Resource<Unit>> = _fetchStatus




    fun getFetchList() {
        viewModelScope.launch {
            _fetchStatus.value = Resource.Loading()
            delay(2000) // Simulate delay if needed

            // Fetch and process the data, updating the database as a side effect
            when (val result = repository.fetchInfo()) {
                is Resource.Success -> {
                    Log.d(TAG, "getFetchList: Info was Successfully Loaded")
                    _fetchStatus.value = Resource.Success(Unit)
                }
                is Resource.Error -> {
                    // Handle the error case by logging and updating the fetchStatus to inform the UI
                    _fetchStatus.value = Resource.Error(result.message ?: "Unknown error")
                    Log.e(TAG, result.message ?: "Unknown error")
                }

                is Resource.Loading -> {}
            }
        }
    }


}