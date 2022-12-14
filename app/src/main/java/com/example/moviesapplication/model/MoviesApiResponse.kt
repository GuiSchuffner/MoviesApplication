package com.example.moviesapplication.model

data class MoviesApiResponse (
    val query: String,
    val results: List<Movies>
)