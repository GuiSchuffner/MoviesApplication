package com.example.moviesapplication.model

data class Movies(
    val id: String,
    val numberOfEpisodes: Int= -1,
    val seriesEndYear: Int = -1,
    val seriesStartYear: Int= -1,
    val title: String,
    val titleType: String,
    val year: Int= -1,
    val principals: List<PrincipalActor>
)