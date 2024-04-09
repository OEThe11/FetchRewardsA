package com.example.fetchrewardsa.screens.individual

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.repository.FetchRewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "IndividualItemViewModel"
@HiltViewModel
class IndividualItemViewModel @Inject constructor(
    private val repository: FetchRewardsRepository
): ViewModel() {

    private val _item = MutableLiveData<FetchItemEntity?>()
    val item: LiveData<FetchItemEntity?> = _item
    val isLoading = mutableStateOf(false)

    fun fetchItemById(itemId: Int){
        viewModelScope.launch {
            isLoading.value = true
            repository.getItemById(itemId)
                .catch {e ->
                    Log.e(TAG, e.message, e.cause )
                    isLoading.value = false
                }
                .collect{itemEntity->
                    _item.value = itemEntity
                    isLoading.value = false
                }

        }
    }
}