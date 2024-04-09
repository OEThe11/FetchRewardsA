package com.example.fetchrewardsa.screens.main

import androidx.collection.emptyLongSet
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.fetchrewardsa.components.CircularIndeterminateProgressBar
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.navigation.FetchRewardsScreens
import com.example.fetchrewardsa.widgets.FetchRewardsCard

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel = hiltViewModel()){
    val fetchItems by viewModel.fetchInfoRewards.observeAsState()

    val loading = viewModel.isLoading.value

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        fetchItems?.let {
            if (loading) CircularIndeterminateProgressBar(isDisplayed = true)
            else MainList(list = it, navController = navController)
        }

    }

}


@Composable
fun MainList(list: List<FetchItemEntity>, navController: NavController){
    LazyColumn {
        items(list){item ->
            FetchRewardsCard(
                fetchItem = item,
                modifier = Modifier.clickable {
                    navController.navigate("${FetchRewardsScreens.IndividualItemScreen.name}/${item.id}")
                }
            )
        }
    }
}