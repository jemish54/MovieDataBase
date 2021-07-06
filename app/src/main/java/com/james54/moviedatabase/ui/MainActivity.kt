package com.james54.moviedatabase.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.james54.moviedatabase.R
import com.james54.moviedatabase.databinding.ActivityMainBinding
import com.james54.moviedatabase.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPopularMovies()

        lifecycleScope.launchWhenStarted {
            viewModel.popularMovies.collect {

                when (it) {
                    MainViewModel.MovieStates.Empty -> binding.progress.visibility = View.VISIBLE
                    is MainViewModel.MovieStates.Failure -> binding.progress.visibility = View.INVISIBLE
                    MainViewModel.MovieStates.Loading -> binding.progress.visibility = View.VISIBLE
                    is MainViewModel.MovieStates.Success -> {
                        binding.progress.visibility = View.INVISIBLE
                        val movieList = it.movieList.results
                        val movieTitleList = ArrayList<String>()
                        movieList.forEach {movie->
                            movieTitleList.add(movie.title)
                        }
                        val adapter = ArrayAdapter(
                            this@MainActivity,
                            R.layout.support_simple_spinner_dropdown_item,
                            movieTitleList
                        )
                        binding.list.adapter = adapter
                    }
                    is MainViewModel.MovieStates.SuccessUpcoming -> {

                    }
                }

            }
        }

    }
}