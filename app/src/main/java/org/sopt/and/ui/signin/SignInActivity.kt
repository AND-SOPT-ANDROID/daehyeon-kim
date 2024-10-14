package org.sopt.and.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.and.ui.my.MyActivity
import org.sopt.and.ui.signup.SignUpActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {

    private var userEmail: String? = null
    private var userPassword: String? = null

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                userEmail = data.getStringExtra("email")
                userPassword = data.getStringExtra("password")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignInScreen(
                    onSignUpClick = {
                        signUpLauncher.launch(
                            Intent(this, SignUpActivity::class.java)
                        )
                    },
                    onSignInClick = { signInInfo, showSnackbar ->
                        when {
                            signInInfo.email == userEmail && signInInfo.password == userPassword -> {
                                showSnackbar("로그인 성공")
                                Intent(this, MyActivity::class.java).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    putExtra("email", signInInfo.email)
                                    startActivity(this)
                                }
                            }
                            signInInfo.email == userEmail -> {
                                showSnackbar("비밀번호가 일치하지 않습니다.")
                            }
                            else -> {
                                showSnackbar("이메일 또는 비밀번호가 일치하지 않습니다.")
                            }
                        }
                    }
                )
            }
        }

        savedInstanceState?.let {
            userEmail = it.getString("USER_EMAIL")
            userPassword = it.getString("USER_PASSWORD")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("USER_EMAIL", userEmail)
        outState.putString("USER_PASSWORD", userPassword)
    }
}
