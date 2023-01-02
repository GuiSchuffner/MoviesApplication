package com.example.moviesapplication.model

data class MovieOverview(
    val id: String,
    val movie: Movies,
    val genres: List<String>,
    val releaseDate: String,
    val plotOutline: PlotOutline,
    val plotSummary: PlotSummary
)
