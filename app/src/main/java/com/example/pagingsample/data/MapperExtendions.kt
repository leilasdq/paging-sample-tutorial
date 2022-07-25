package com.example.pagingsample.data

import com.example.pagingsample.local.MoviesEntity
import com.example.pagingsample.model.Movies
import com.example.pagingsample.network.MoviesDto

fun MoviesDto.toDomain(): Movies =
    Movies(id, title, poster, genres, images, year, country, rating)

fun MoviesDto.toEntity(): MoviesEntity =
    MoviesEntity(id, title, poster, genres, images, year, country, rating)

fun MoviesEntity.toDomain(): Movies =
    Movies(id, title, poster, genres, images, year, country, rating)