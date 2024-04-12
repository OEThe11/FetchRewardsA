package com.example.fetchrewardsa.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.repository.FetchRewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FetchRewardsRepository
) : ViewModel() {

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
                    _fetchStatus.value = Resource.Success(Unit)
                }

                is Resource.Error -> {
                    _fetchStatus.value = Resource.Error(result.message ?: "Unknown error")
                }

                is Resource.Loading -> {}
            }
        }
    }


}