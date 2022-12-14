package com.example.moviesapplication.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesHomeContent(moviesViewModel: MoviesHomeViewModel = koinViewModel()){
    Column(modifier = Modifier.fillMaxSize()) {
        MoviesSearch(
            moviesFieldValue = moviesViewModel.inputMovies,
            onValueChanged = { moviesViewModel.onInputMoviesChanged(it) },
            onButtonClicked = { moviesViewModel.onSearchButtonClicked() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesSearch(
    moviesFieldValue: String, onValueChanged: (String)->Unit, onButtonClicked: ()->Unit
) {
    Row( modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically ) {
        OutlinedTextField(
            value = moviesFieldValue,
            onValueChange = { onValueChanged(it) },
            label = { SearchMovieLabel() },
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {onButtonClicked()})
        )
    }
}
@Preview
@Composable
fun PreviewMoviesSearch(){
    MoviesSearch(moviesFieldValue = "", onValueChanged = {  }, onButtonClicked = {})
}

@Composable
fun SearchMovieLabel(){
    Text(text = "Search a Movie")
}


