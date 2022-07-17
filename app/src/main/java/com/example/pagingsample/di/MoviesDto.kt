package com.example.pagingsample.di

data class MoviesDto(
    val id: Int,
    val title: String,
    val poster: String,
    val genres: List<String>,
    val images: List<String>
)
