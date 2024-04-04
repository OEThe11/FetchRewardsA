package com.example.fetchrewardsa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fetchrewardsa.screens.main.MainScreen

@Composable
fun FetchRewardsNavi() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FetchRewardsScreens.MainScreen.name
    ){
        composable(FetchRewardsScreens.MainScreen.name){
          MainScreen()
        }

    }
}