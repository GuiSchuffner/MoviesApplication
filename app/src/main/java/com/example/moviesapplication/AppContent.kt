package com.example.moviesapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
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
            ) {
                navController.navigateUp()
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isBottomBarDestination: Boolean, onNavIconClicked: ()->Unit) {
    CenterAlignedTopAppBar(
        title = { TopBarText() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            scrolledContainerColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            if(!isBottomBarDestination) {
                IconButton(onClick = { onNavIconClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun TopBarPreview(){
    TopBar(isBottomBarDestination = true, onNavIconClicked = {})
}

@Composable
fun TopBarText(){
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Movies Application")
    }
}
