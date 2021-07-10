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
import com.james54.moviedatabase.databinding.FragmentTopRatedMoviesBinding
import com.james54.moviedatabase.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment(R.layout.fragment_top_rated_movies) {

    private var _binding: FragmentTopRatedMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedMoviesBinding.inflate(inflater,container,false)
        val view = binding.root

        lifecycleScope.launchWhenStarted {
            viewModel.topRatedMovies.collect{
                when(it){
                    MainViewModel.MovieStates.Empty -> {}
                    is MainViewModel.MovieStates.Failure -> {
                        binding.progress.visibility = View.INVISIBLE
                        binding.rvTopRatedMovies.visibility = View.INVISIBLE
                        val message = it.message
                        Snackbar.make(view,message, Snackbar.LENGTH_LONG)
                            .show()
                    }
                    MainViewModel.MovieStates.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.rvTopRatedMovies.visibility = View.INVISIBLE
                    }
                    is MainViewModel.MovieStates.Success -> {
                        binding.progress.visibility = View.INVISIBLE
                        binding.rvTopRatedMovies.visibility = View.VISIBLE
                        val adapter = MovieListAdapter(it.movieList.results)
                        binding.rvTopRatedMovies.setHasFixedSize(true)
                        binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(context)
                        binding.rvTopRatedMovies.adapter = adapter
                    }
                    is MainViewModel.MovieStates.SuccessUpcoming -> {}
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