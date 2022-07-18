package com.example.pagingsample.network

import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("api/v1/movies")
    suspend fun getPagedMovies(
        @Query("page") id: Int,
    ): BaseDto
}