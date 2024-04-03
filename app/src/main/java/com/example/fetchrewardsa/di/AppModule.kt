package com.example.fetchrewardsa.di

import android.content.Context
import androidx.room.Room
import com.example.fetchrewardsa.Constants.BASE_URL
import com.example.fetchrewardsa.database.FetchRewardsDao
import com.example.fetchrewardsa.database.FetchRewardsDatabase
import com.example.fetchrewardsa.network.FetchRewardsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFetchDao(fetchRewardsDatabase: FetchRewardsDatabase): FetchRewardsDao =
        fetchRewardsDatabase.fetchRewardsDao()


    @Provides
    @Singleton
    fun provideFetchDatabase(@ApplicationContext context: Context): FetchRewardsDatabase =
        Room.databaseBuilder(
            context,
            FetchRewardsDatabase::class.java,
            "fetchInfo"
        )
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun providesFetchRewardsApi(): FetchRewardsApi{
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FetchRewardsApi::class.java)
    }

}