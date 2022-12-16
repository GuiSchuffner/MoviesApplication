package com.example.moviesapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.moviesapplication.ui.theme.Gray
import com.example.moviesapplication.ui.theme.Yellow

@Composable
fun AppContent(){

}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = { TopBarText() },
        navigationIcon = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Gray)
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

@Composable
fun NavigationSetup(navController: NavHostController){

}
