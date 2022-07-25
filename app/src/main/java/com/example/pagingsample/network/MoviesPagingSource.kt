package com.example.pagingsample.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingsample.data.toDomain
import com.example.pagingsample.data.toEntity
import com.example.pagingsample.local.MoviesEntity
import com.example.pagingsample.model.Movies
import java.lang.Exception

class MoviesPagingSource(private val service: MoviesService): PagingSource<Int, MoviesEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesEntity>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesEntity> {
        val position = if (params.key == 0) INITIAL_PAGE_POSITION else params.key ?: INITIAL_PAGE_POSITION
        return try {
            val data = service.getPagedMovies(position).data.map {
                it.toEntity()
            }
            val nextKey = position+1
            val prevKey = if (position == INITIAL_PAGE_POSITION) null else position-1

            Log.d("movieee", "load: pos: $position, prev: $prevKey, next, $nextKey")

            LoadResult.Page(
                data, prevKey, nextKey
            )
        } catch (e: Exception) {
            Log.d("movieee", "load error: ${e.message}")
            LoadResult.Error(
                e
            )
        }
    }

    companion object {
        private const val INITIAL_PAGE_POSITION = 1
    }
}