package org.sopt.and.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.and.R
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
                userEmail = data.getStringExtra(EMAIL)
                userPassword = data.getStringExtra(PASSWORD)
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
                                showSnackbar(getString(R.string.login_success))
                                Intent(this, MyActivity::class.java).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    putExtra(EMAIL, signInInfo.email)
                                    startActivity(this)
                                }
                            }
                            signInInfo.email == userEmail -> {
                                showSnackbar(getString(R.string.incorrect_password))
                            }
                            else -> {
                                showSnackbar(getString(R.string.incorrect_email_or_password))
                            }
                        }
                    }
                )
            }
        }

        savedInstanceState?.let {
            userEmail = it.getString(USER_EMAIL)
            userPassword = it.getString(USER_PASSWORD)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_EMAIL, userEmail)
        outState.putString(USER_PASSWORD, userPassword)
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val USER_EMAIL = "USER_EMAIL"
        private const val USER_PASSWORD = "USER_PASSWORD"
    }
}
