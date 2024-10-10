package org.sopt.and.ui.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userEmail = intent.getStringExtra("email") ?: ""

        setContent{
            ANDANDROIDTheme {
                MyScreen(
                    userName = userEmail
                )
            }
        }
    }
}
