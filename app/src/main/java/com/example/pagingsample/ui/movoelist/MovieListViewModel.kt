package com.example.pagingsample.ui.movoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagingsample.data.MoviesRepository
import com.example.pagingsample.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repo: MoviesRepository
): ViewModel() {

    private val _movieListData = MutableLiveData<List<Movies>>()
    val movieList: LiveData<List<Movies>> = _movieListData

    init {
        viewModelScope.launch {
            repo.getAllMoviesByPage().collect { data ->
                _movieListData.postValue(data)
            }
        }
    }
}