package com.example.moviesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviesapplication.ui.theme.MoviesApplicationTheme

class InitialActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesApplicationTheme {
                AppContent()
            }
        }
    }
}
