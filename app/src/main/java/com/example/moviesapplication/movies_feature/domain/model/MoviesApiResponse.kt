package com.example.moviesapplication.movies_feature.domain.model

data class MoviesApiResponse (
    val query: String,
    val results: List<Movies>
)
