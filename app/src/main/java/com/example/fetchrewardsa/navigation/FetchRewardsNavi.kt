package com.example.fetchrewardsa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fetchrewardsa.screens.individual.IndividualItemScreen
import com.example.fetchrewardsa.screens.main.MainScreen

@Composable
fun FetchRewardsNavi() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FetchRewardsScreens.MainScreen.name
    ) {
        composable(FetchRewardsScreens.MainScreen.name) {
            MainScreen(navController = navController)
        }

        composable(
            "${FetchRewardsScreens.IndividualItemScreen.name}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            itemId?.let {
                IndividualItemScreen(
                    itemId = it,
                    navController = navController
                )
            }
        }

    }
}