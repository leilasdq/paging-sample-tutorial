package com.example.pagingsample.model

data class Movies(
    val id: Int,
    val title: String,
    val poster: String,
    val genres: List<String>,
    val images: List<String>
)
