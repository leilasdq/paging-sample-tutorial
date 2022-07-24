package com.example.pagingsample.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MoviesEntity>)

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): PagingSource<Int, MoviesEntity>
}