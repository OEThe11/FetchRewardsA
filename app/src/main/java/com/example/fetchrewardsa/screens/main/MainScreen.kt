package com.example.fetchrewardsa.screens.main

import android.util.Log
import androidx.collection.emptyLongSet
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fetchrewardsa.components.CircularIndeterminateProgressBar
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.database.FetchItemEntity
import com.example.fetchrewardsa.navigation.FetchRewardsScreens
import com.example.fetchrewardsa.widgets.FetchRewardsCard

private const val TAG = "MainScreen"

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel = hiltViewModel()){
    // Observing feeds directly for database changes
    val fetchItems by viewModel.fetchInfoRewards.collectAsState()

    // Observing the fetch status for loading and error states
    val fetchStatus by viewModel.fetchStatus.collectAsState()
    
    LaunchedEffect(true) {
        viewModel.getFetchList()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        when (fetchStatus){
            is Resource.Loading -> CircularIndeterminateProgressBar(isDisplayed = true)
            is Resource.Error -> {
                Text(text = "Error: ${(fetchStatus as Resource.Error).message}", color = Color.Red)
            }
            is Resource.Success -> {
                MainList(list = fetchItems, navController = navController)
                Log.d(TAG, "MainScreen: Successful Loading")
            }
        
        
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
                    Log.d(TAG, "MainList: Item has been clicked, Item transferred is ${item.id}")
                }
            )
        }
    }
}