package com.example.moviesapplication.movies_feature.domain.model

data class MovieOverview(
    val id: String,
    val title: Movies,
    val genres: List<String>,
    val releaseDate: String,
    val plotOutline: PlotOutline,
    val plotSummary: PlotSummary,
    val rating: MovieRatings?
)
