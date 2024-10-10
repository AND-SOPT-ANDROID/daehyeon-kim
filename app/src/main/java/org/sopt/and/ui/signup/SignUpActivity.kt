package org.sopt.and.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.and.ui.signin.SignInActivity
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
                    onSignUp = { email, password ->
                        when {
                            !isEmailValid(email) -> {
                                Toast.makeText(this, "잘못된 이메일 형식입니다.", Toast.LENGTH_SHORT).show()
                            }
                            !isPasswordValid(password) -> {
                                Toast.makeText(this, "잘못된 비밀번호 형식입니다.", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Intent(this, SignInActivity::class.java).apply {
                                    putExtra("email", email)
                                    putExtra("password", password)
                                    startActivity(this)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
