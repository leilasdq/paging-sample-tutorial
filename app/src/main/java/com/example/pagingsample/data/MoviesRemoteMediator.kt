package com.example.pagingsample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingsample.local.MoviesDao
import com.example.pagingsample.local.MoviesEntity
import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesService
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val remoteService: MoviesService, private val local: MoviesDao
): RemoteMediator<Int, MoviesEntity>() {
    var startingPage = 1
    override suspend fun load(loadType: LoadType, state: PagingState<Int, MoviesEntity>): MediatorResult {
        return try {
            val remoteData = remoteService.getPagedMovies(startingPage)
            val isEndOfList = remoteData.data.isNullOrEmpty()
            local.insertAllMovies(remoteData.data.map {
                it.toEntity()
            })

            MediatorResult.Success(isEndOfList)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}