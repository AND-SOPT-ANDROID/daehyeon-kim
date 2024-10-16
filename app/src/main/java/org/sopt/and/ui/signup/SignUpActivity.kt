package org.sopt.and.ui.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.util.isEmailValid
import org.sopt.and.util.isPasswordValid

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpScreen(
                    onSignUpClick = { email, password ->
                        when {
                            !isEmailValid(email) -> {
                                Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show()
                            }
                            !isPasswordValid(password) -> {
                                Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                val resultIntent = Intent().apply {
                                    putExtra(EMAIL, email)
                                    putExtra(PASSWORD, password)
                                }
                                setResult(Activity.RESULT_OK, resultIntent)
                                finish()
                            }
                        }
                    }
                )
            }
        }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}
