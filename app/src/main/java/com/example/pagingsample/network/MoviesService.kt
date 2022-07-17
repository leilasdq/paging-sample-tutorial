package com.example.pagingsample.network

import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("api/v1/movies")
    suspend fun getPAgedMovies(
        @Query("page") id: Int,
    ): BaseDto
}