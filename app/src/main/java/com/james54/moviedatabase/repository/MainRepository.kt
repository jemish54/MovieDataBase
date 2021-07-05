package com.james54.moviedatabase.repository

import com.james54.moviedatabase.models.MovieResponse
import com.james54.moviedatabase.models.UpcomingMovieResponse
import com.james54.moviedatabase.util.Resource

interface MainRepository {

    suspend fun getPopular():Resource<MovieResponse>

    suspend fun getTopRated():Resource<MovieResponse>

    suspend fun getUpcoming():Resource<UpcomingMovieResponse>

}