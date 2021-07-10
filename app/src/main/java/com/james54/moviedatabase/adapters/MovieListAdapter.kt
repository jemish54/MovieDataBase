package com.james54.moviedatabase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.james54.moviedatabase.databinding.MovieItemBinding
import com.james54.moviedatabase.models.Movie
import com.james54.moviedatabase.util.Constants.Companion.IMAGE_BASE

class MovieListAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            val movie = movieList[position]
            movieTitle.text = movie.title
            movieRating.text = movie.vote_average.toString()
            movieReleaseDate.text = movie.release_date
            movieCoverImage.load(IMAGE_BASE+movie.poster_path){
                crossfade(true)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

