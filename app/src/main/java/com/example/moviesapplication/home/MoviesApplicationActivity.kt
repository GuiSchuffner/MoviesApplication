package com.example.moviesapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.moviesapplication.ui.theme.MoviesApplicationTheme


class MoviesApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesApplicationTheme {
                MoviesHomeContent()
            }
        }
    }
}
