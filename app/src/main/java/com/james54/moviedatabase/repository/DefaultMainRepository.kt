package com.james54.moviedatabase.repository

import com.james54.moviedatabase.models.MovieResponse
import com.james54.moviedatabase.models.UpcomingMovieResponse
import com.james54.moviedatabase.network.MovieApi
import com.james54.moviedatabase.util.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    val movieApi: MovieApi
) : MainRepository{

    override suspend fun getPopular(): Resource<MovieResponse> {
        return try{
            val response = movieApi.getPopular()
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }catch(e:Exception){
            Resource.Error(e.message ?: "An Unknown Error Occurred")
        }
    }

    override suspend fun getTopRated(): Resource<MovieResponse> {
        return try{
            val response = movieApi.getTopRated()
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }catch(e:Exception){
            Resource.Error(e.message ?: "An Unknown Error Occurred")
        }
    }

    override suspend fun getUpcoming(): Resource<UpcomingMovieResponse> {
        return try{
            val response = movieApi.getUpcoming()
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }catch(e:Exception){
            Resource.Error(e.message ?: "An Unknown Error Occurred")
        }
    }

}