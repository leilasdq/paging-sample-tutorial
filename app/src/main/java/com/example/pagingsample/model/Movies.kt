package com.example.pagingsample.model

import com.google.gson.annotations.SerializedName

data class Movies(
    val id: Int?,
    val title: String?,
    val poster: String?,
    val genres: List<String>?,
    val images: List<String>?,
    val year: String?,
    val country: String?,
    val rating: String?,
)
