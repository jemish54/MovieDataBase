package com.james54.moviedatabase.di

import com.james54.moviedatabase.network.MovieApi
import com.james54.moviedatabase.repository.DefaultMainRepository
import com.james54.moviedatabase.repository.MainRepository
import com.james54.moviedatabase.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi():MovieApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api:MovieApi):MainRepository = DefaultMainRepository(api)

}