package com.example.moviesapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

sealed class NavRoutes(val route: String) {
    object Home: NavRoutes("home")
    object MoviesGenres: NavRoutes("movies_genres")
}

@Composable
fun NavigationSetup(navController: NavHostController){
    NavHost(navController = navController, startDestination = )
}

sealed class BottomNavItem(
    val route: String,
    val icon: Int
) {
    object Home: BottomNavItem(NavRoutes.Home.route, R.drawable.ic_movie_icon)
    object MoviesGenre: BottomNavItem(NavRoutes.MoviesGenres.route, R.drawable.ic_genre_icon)
}
