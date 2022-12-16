package com.example.moviesapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapplication.ui.theme.MoviesApplicationTheme

class InitialActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesApplicationTheme {
                AppContent()
            }
        }
    }
}