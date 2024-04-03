package com.example.fetchrewardsa.network

import com.example.fetchrewardsa.Constants.ENDPOINT
import com.example.fetchrewardsa.models.FetchGetResponseItem
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface FetchRewardsApi {
    @GET(ENDPOINT)
    suspend fun getAllFetchRewardsInfo(): Response<List<FetchGetResponseItem>>
}