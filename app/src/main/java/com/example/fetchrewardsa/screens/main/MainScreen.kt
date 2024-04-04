package com.example.fetchrewardsa.screens.main

import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetchrewardsa.components.CircularIndeterminateProgressBar
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.widgets.FetchRewardsCard

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()){
    val fetchItems by viewModel.fetchInfoRewards.observeAsState()

    val loading = viewModel.isLoading.value

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        fetchItems?.let {
            if (loading) CircularIndeterminateProgressBar(isDisplayed = true)
            else MainList(list = it)
        }

    }

}


@Composable
fun MainList(list: List<FetchItemEntity>){
    LazyColumn {
        items(list){item ->
            FetchRewardsCard(fetchItem = item)
        }
    }
}