package com.example.fetchrewardsa.screens.individual

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.repository.FetchRewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "IndividualItemViewModel"
@HiltViewModel
class IndividualItemViewModel @Inject constructor(
    private val repository: FetchRewardsRepository
): ViewModel() {

    private val _item = MutableStateFlow<Resource<FetchItemEntity>>(Resource.Loading())
    val item: StateFlow<Resource<FetchItemEntity>> = _item



    fun fetchEntityById(id: Int){
        viewModelScope.launch {
            repository.getById(id).collect { resource ->
                _item.value = resource
            }
        }
    }
}