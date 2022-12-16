package com.example.moviesapplication

sealed class NavRoutes(val route: String) {
    object Home: NavRoutes("home")
    object MoviesGenres: NavRoutes("genres")
}
