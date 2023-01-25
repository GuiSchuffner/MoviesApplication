package com.example.moviesapplication.movies_feature.domain.model

data class PrincipalActor(
    val id: String,
    val legacyNameText: String,
    val category: String,
    val characters: List<String>,
    val endYear: Int,
    val episodeCount: Int,
    val startYear: Int
)
