package com.example.moviesapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesapplication.ui.theme.Navy
import com.example.moviesapplication.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(){
    val navController = rememberNavController()
    val bottomBarScreens = listOf (
        BottomNavItem.Home.route,
        BottomNavItem.MoviesGenre.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = bottomBarScreens.any { it == currentDestination?.route }
    Scaffold(
        bottomBar = { if(bottomBarDestination) BottomNav(navController) },
        topBar = {
            TopBar(
                (bottomBarDestination || navController.previousBackStackEntry == null)
            ) { navController.navigateUp() }
        },
        containerColor = Color.Black
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isBottomBarDestination: Boolean, onNavIconClicked: ()->Unit){
    TopAppBar(
        title = { TopBarText() },
        navigationIcon = {
                         if(!isBottomBarDestination) {
                             IconButton(onClick = { onNavIconClicked() }) {
                                 Icon(
                                     imageVector = Icons.Filled.ArrowBack,
                                     contentDescription = "Back"
                                 )
                             }
                         }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Navy)
    )
}

@Composable
fun TopBarText(){
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Movies Application", color = Yellow)
    }
}
