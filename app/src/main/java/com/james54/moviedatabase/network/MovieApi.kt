package com.james54.moviedatabase.network

import com.james54.moviedatabase.models.MovieResponse
import com.james54.moviedatabase.models.UpcomingMovieResponse
import com.james54.moviedatabase.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular?api_key=${API_KEY}")
    suspend fun getPopular():Response<MovieResponse>

    @GET("movie/top_rated?api_key=${API_KEY}")
    suspend fun getTopRated():Response<MovieResponse>

    @GET("movie/upcoming?api_key=${API_KEY}")
    suspend fun getUpcoming():Response<UpcomingMovieResponse>

}