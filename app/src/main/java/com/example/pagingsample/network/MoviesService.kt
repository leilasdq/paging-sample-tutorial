package com.example.pagingsample.network

import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {

    @GET("api/v1/movies?page={page}")
    suspend fun getPAgedMovies(
        @Query("page") page: Int
    ): BaseDto
}