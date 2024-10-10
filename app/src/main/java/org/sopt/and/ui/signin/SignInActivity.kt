package org.sopt.and.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.and.ui.my.MyActivity
import org.sopt.and.ui.signup.SignUpActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userEmail = intent.getStringExtra("email") ?: ""
        val userPassword = intent.getStringExtra("password") ?: ""

        setContent {
            ANDANDROIDTheme {
                SignInScreen(
                    onSignUpClick = {
                        Intent(this, SignUpActivity::class.java).apply {
                            startActivity(this)
                        }
                    },
                    onSignInClick = { inputEmail, inputPassword ->
                        when {
                            inputEmail == userEmail && inputPassword == userPassword -> {
                                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                                Intent(this, MyActivity::class.java).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    putExtra("email", inputEmail)
                                    startActivity(this)
                                }
                            }
                            inputEmail == userEmail -> {
                                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(this, "이메일 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
            }
        }
    }
}
