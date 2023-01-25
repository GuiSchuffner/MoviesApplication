package com.example.moviesapplication

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.moviesapplication.movies_feature.presentation.genres.MoviesGenresContent
import com.example.moviesapplication.movies_feature.presentation.home.MoviesHomeContent
import com.example.moviesapplication.movies_feature.presentation.moviedetails.MovieDetailsContent
import com.example.moviesapplication.movies_feature.presentation.moviedetails.MovieDetailsViewModel
import com.example.moviesapplication.ui.theme.Navy
import com.example.moviesapplication.ui.theme.Yellow
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun BottomNav(navController: NavHostController) {

    val navigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.MoviesGenre
    )

    BottomNavigation(backgroundColor = Navy, contentColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = null) },
                selectedContentColor = Yellow,
                unselectedContentColor = Yellow.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination  = BottomNavItem.Home.route) {
        composable(route = BottomNavItem.Home.route) {
            MoviesHomeContent(
                navigateToMovieDetails = {
                    navController.navigate(AppRoutes.MovieDetails.name + "/$it")
                }
            )
        }
        composable(route = BottomNavItem.MoviesGenre.route) {
            MoviesGenresContent()
        }
        composable(
            route = AppRoutes.MovieDetails.name + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
                nullable = false
            })
        ) {
            getViewModel<MovieDetailsViewModel>(parameters = {
                parametersOf(it.arguments?.getString("movieId")!!)
            })
            MovieDetailsContent(it.arguments?.getString("movieId")!!)
        }
    }
}

enum class AppRoutes {
    MovieDetails
}

sealed class BottomNavItem(
    val route: String,
    val icon: Int
) {
    object Home: BottomNavItem("Home", R.drawable.ic_movie_icon)
    object MoviesGenre: BottomNavItem("MoviesGenres", R.drawable.ic_genre_icon)
}
