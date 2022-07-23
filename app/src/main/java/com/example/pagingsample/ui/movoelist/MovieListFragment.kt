package com.example.pagingsample.ui.movoelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentMovieListBinding
import com.example.pagingsample.utils.showShortToast

@AndroidEntryPoint
class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val TAG = "leila_Movies"
    private val viewModelMovie: MovieListViewModel by viewModels()

    private lateinit var adapter: MovieListAdapter
    lateinit var binding: FragmentMovieListBinding

    private val adapterLoadStateListener: (CombinedLoadStates) -> Unit = { loadState ->
        val isEmptyList = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
        binding.moviesList.isVisible = isEmptyList.not()
        binding.imgEmptyList.isVisible = isEmptyList

        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorState?.let {
            it.error.message?.let { msg -> requireContext().showShortToast(msg) }
        }
    }

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
        adapter.addLoadStateListener(adapterLoadStateListener)
        binding.moviesList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MoviesLoadStateAdapter { movieListRetryFunction() },
            footer = MoviesLoadStateAdapter { movieListRetryFunction() }
        )

        viewModelMovie.movieList.observe(viewLifecycleOwner) { movies ->
            adapter.submitData(lifecycle, movies)
        }
    }

    override fun onStop() {
        adapter.removeLoadStateListener(adapterLoadStateListener)
        super.onStop()
    }

    private fun movieListRetryFunction() {
        adapter.retry()
    }
}