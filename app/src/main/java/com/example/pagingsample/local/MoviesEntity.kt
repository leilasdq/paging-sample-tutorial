package com.example.pagingsample.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MoviesEntity(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val poster: String?,
    val genres: List<String>?,
    val images: List<String>?,
    val year: String?,
    val country: String?,
    val rating: String?,
)
