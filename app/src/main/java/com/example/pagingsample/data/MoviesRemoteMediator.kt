package com.example.pagingsample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingsample.local.MoviesDao
import com.example.pagingsample.local.MoviesEntity
import com.example.pagingsample.network.MoviesService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val remoteService: MoviesService, private val localDao: MoviesDao
): RemoteMediator<Int, MoviesEntity>() {
    var offset = 1
    override suspend fun load(loadType: LoadType, state: PagingState<Int, MoviesEntity>): MediatorResult {
        return try {
            // The network load method takes an optional `after=<user.id>` parameter. For every
            // page after the first, we pass the last user ID to let it continue from where it
            // left off. For REFRESH, pass `null` to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> offset
                // In this example, we never need to prepend, since REFRESH will always load the
                // first page in the list. Immediately return, reporting end of pagination.
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    // We must explicitly check if the last item is `null` when appending,
                    // since passing `null` to networkService is only valid for initial load.
                    // If lastItem is `null` it means no items were loaded after the initial
                    // REFRESH and there are no more items to load.
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    offset += 1
                    offset
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be wrapped in a
            // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine CallAdapter
            // dispatches on a worker thread.
            val response = loadKey.let { remoteService.getPagedMovies(it) }

            response.data.map {
                it.toEntity()
            }.let { localDao.insertAllMovies(it) }

            MediatorResult.Success(endOfPaginationReached = response.data.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
       return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}