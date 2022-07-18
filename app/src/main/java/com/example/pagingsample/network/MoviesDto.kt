package com.example.pagingsample.network

import com.google.gson.annotations.SerializedName

data class MoviesDto(
    val id: Int,
    val title: String,
    val poster: String,
    val genres: List<String>,
    val images: List<String>,
    val year: String,
    val country: String,
    @SerializedName("imdb_rating")
    val rating: String,
)
