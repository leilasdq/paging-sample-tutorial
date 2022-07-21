package com.example.pagingsample.ui.movoelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentMovieListBinding

@AndroidEntryPoint
class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val TAG = "leila_Movies"
    private val viewModelMovie: MovieListViewModel by viewModels()

    private lateinit var adapter: MovieListAdapter
    lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieListAdapter()
        binding.moviesList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MoviesLoadStateAdapter { movieListRetryFunction() },
            footer = MoviesLoadStateAdapter { movieListRetryFunction() }
        )

        viewModelMovie.movieList.observe(viewLifecycleOwner) { movies ->
            adapter.submitData(lifecycle, movies)
        }
    }

    fun movieListRetryFunction() {
        adapter.retry()
    }
}