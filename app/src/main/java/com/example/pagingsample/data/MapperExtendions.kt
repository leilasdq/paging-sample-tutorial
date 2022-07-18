package com.example.pagingsample.data

import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesDto

fun MoviesDto.toDomain(): Movies =
    Movies(id, title, poster, genres, images, year, country, rating)