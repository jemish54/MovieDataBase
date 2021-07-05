package com.james54.moviedatabase.viewmodels

import androidx.lifecycle.ViewModel
import com.james54.moviedatabase.models.Movie
import com.james54.moviedatabase.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    sealed class MovieStates{
        class Success(val movieList:List<Movie>):MovieStates()
        class Failure(val message:String):MovieStates()
        object Loading:MovieStates()
        object Empty:MovieStates()
    }

    val _movieStateFlow = MutableStateFlow(MovieStates.Empty)
    val movieStateFlow:StateFlow<MovieStates> = _movieStateFlow

    

}