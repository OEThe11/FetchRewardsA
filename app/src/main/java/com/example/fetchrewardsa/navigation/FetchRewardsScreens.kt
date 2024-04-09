package com.example.fetchrewardsa.navigation

enum class FetchRewardsScreens {
    MainScreen,
    IndividualItemScreen;

    companion object {
        fun fromRoute(route: String?): FetchRewardsScreens
                = when (route?.substringBefore("/")) {
            MainScreen.name -> MainScreen
            IndividualItemScreen.name -> IndividualItemScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}