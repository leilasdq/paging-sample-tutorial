package com.example.pagingsample.data

import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MoviesRepository {

    suspend fun getAllMoviesByPage(): Flow<List<Movies>>
}

class MoviesRepositoryImpl(
    private val remoteService: MoviesService
): MoviesRepository {
    override suspend fun getAllMoviesByPage(): Flow<List<Movies>> = flow {
        val data = remoteService.getPAgedMovies(1).data.map {
            it.toDomain()
        }
        emit(data)
    }

}