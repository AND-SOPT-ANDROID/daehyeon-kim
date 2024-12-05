package org.sopt.and.ui.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.ui.signup.component.SignUpBottomBar
import org.sopt.and.ui.signup.component.SignUpTopBar
import org.sopt.and.ui.signup.component.section.InputHobbySection
import org.sopt.and.ui.signup.component.section.InputPasswordSection
import org.sopt.and.ui.signup.component.section.InputUserNameSection

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigationToSignIn: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = viewModel.snackbarMessage

    LaunchedEffect(viewModel.shouldNavigateToSignIn) {
        if (viewModel.shouldNavigateToSignIn) {
            viewModel.onSignUpComplete()
            navigationToSignIn()
        }
    }

    if (snackbarMessage.isNotEmpty()) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(snackbarMessage)
            viewModel.clearSnackbarMessage()
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = { SignUpTopBar() },
        bottomBar = {
            SignUpBottomBar(
                isSignUpButtonEnabled = viewModel.isSignUpButtonEnabled(),
                onSignUpClick = { viewModel.signUp() }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black,
        content = { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                SignUpContent(
                    modifier = Modifier.padding(padding),
                    userName = viewModel.userName,
                    onUserNameChanged = { viewModel.onUserNameChanged(it) },
                    password = viewModel.password,
                    onPasswordChanged = { viewModel.onPasswordChanged(it) },
                    showPassword = viewModel.showPassword,
                    onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() },
                    hobby = viewModel.hobby,
                    onHobbyChanged = { viewModel.onHobbyChanged(it) }
                )

                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        color = Color.White
                    )
                }
            }
        }
    )
}

@Composable
private fun SignUpContent(
    modifier: Modifier,
    userName: String,
    onUserNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hobby: String,
    onHobbyChanged: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 25.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.welcome_message),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputUserNameSection(
            userName = userName,
            onUserNameChanged = onUserNameChanged
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputPasswordSection(
            password = password,
            onPasswordChanged = onPasswordChanged,
            showPassword = showPassword,
            onTogglePasswordVisibility = onTogglePasswordVisibility,
        )

        Spacer(modifier = Modifier.padding(12.dp))

        InputHobbySection(
            hobby = hobby,
            onHobbyChanged = onHobbyChanged
        )
    }
}

@Preview
@Composable
private fun PreViewSignUpScreen() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}
