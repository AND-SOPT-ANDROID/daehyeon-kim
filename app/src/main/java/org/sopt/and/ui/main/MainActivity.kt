package org.sopt.and.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = false
            }

            ANDANDROIDTheme {
                MainScreen()
            }
        }
    }
}
