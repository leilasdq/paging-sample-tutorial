package com.example.pagingsample.data

import androidx.paging.*
import com.example.pagingsample.local.MoviesDao
import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesPagingSource
import com.example.pagingsample.network.MoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface MoviesRepository {

    fun getAllMoviesByPage(): Flow<PagingData<Movies>>
}

class MoviesRepositoryImpl(
    private val remoteService: MoviesService,
    private val localDao: MoviesDao
): MoviesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllMoviesByPage(): Flow<PagingData<Movies>> = Pager(
        config = PagingConfig(
            pageSize = 8,
            enablePlaceholders = false,
            initialLoadSize = 8
        ),
        pagingSourceFactory = {
            localDao.getAllMovies()
        },
        remoteMediator = MoviesRemoteMediator(remoteService, localDao),
        initialKey = null
    ).flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }

}