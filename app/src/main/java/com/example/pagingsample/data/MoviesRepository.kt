package com.example.pagingsample.data

import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MoviesRepository {

    suspend fun getAllMoviesByPage(): Flow<Movies>
}

class MoviesRepositoryImpl(
    val remoteService: MoviesService
): MoviesRepository {
    override suspend fun getAllMoviesByPage(): Flow<Movies> {

    }

}