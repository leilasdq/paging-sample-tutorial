package com.example.pagingsample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesPagingSource
import com.example.pagingsample.network.MoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MoviesRepository {

    fun getAllMoviesByPage(): Flow<PagingData<Movies>>
}

class MoviesRepositoryImpl(
    private val remoteService: MoviesService
): MoviesRepository {
    override fun getAllMoviesByPage(): Flow<PagingData<Movies>> = Pager(
        config = PagingConfig(
            pageSize = 8,
            enablePlaceholders = false,
            initialLoadSize = 8
        ),
        pagingSourceFactory = {
            MoviesPagingSource(remoteService)
        }
    ).flow

}