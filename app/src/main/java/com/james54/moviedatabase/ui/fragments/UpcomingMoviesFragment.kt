package com.james54.moviedatabase.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.james54.moviedatabase.R
import com.james54.moviedatabase.adapters.MovieListAdapter
import com.james54.moviedatabase.databinding.FragmentUpcomingMoviesBinding
import com.james54.moviedatabase.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UpcomingMoviesFragment : Fragment(R.layout.fragment_upcoming_movies) {

    private var _binding: FragmentUpcomingMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingMoviesBinding.inflate(inflater,container,false)
        val view = binding.root

        lifecycleScope.launchWhenStarted {
            viewModel.upcomingMovies.collect{
                when(it){
                    MainViewModel.MovieStates.Empty -> {}
                    is MainViewModel.MovieStates.Failure -> {
                        binding.progress.visibility = View.INVISIBLE
                        binding.rvUpcomingMovies.visibility = View.INVISIBLE
                        val message = it.message
                        Snackbar.make(view,message, Snackbar.LENGTH_LONG)
                            .show()
                    }
                    MainViewModel.MovieStates.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.rvUpcomingMovies.visibility = View.INVISIBLE
                    }
                    is MainViewModel.MovieStates.Success -> { }
                    is MainViewModel.MovieStates.SuccessUpcoming -> {
                        binding.progress.visibility = View.INVISIBLE
                        binding.rvUpcomingMovies.visibility = View.VISIBLE
                        val adapter = MovieListAdapter(it.movieList.results)
                        binding.rvUpcomingMovies.setHasFixedSize(true)
                        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(context)
                        binding.rvUpcomingMovies.adapter = adapter
                    }
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}