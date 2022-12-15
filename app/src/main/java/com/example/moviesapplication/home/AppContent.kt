package com.example.moviesapplication.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppContent(){

}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {TopBarText()},
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Gray)
    )
}

@Composable
fun TopBarText(){
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Movies Application", color = Color.White)
    }
}
