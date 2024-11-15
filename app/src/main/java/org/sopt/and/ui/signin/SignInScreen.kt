package org.sopt.and.ui.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.ui.signin.component.InputPasswordSection
import org.sopt.and.ui.signin.component.InputUserNameSection
import org.sopt.and.ui.signin.component.SignInTopBar

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigationToHome: () -> Unit = {},
    navigationToSignUp: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = viewModel.snackbarMessage
    val isLoggedIn = viewModel.isLoggedIn

    if (snackbarMessage.isNotEmpty()) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(snackbarMessage)
            viewModel.clearSnackbarMessage()
        }
    }

    if (isLoggedIn) {
        LaunchedEffect(isLoggedIn) {
            navigationToHome()
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = { SignInTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black,
        content = { innerPadding ->
            SignInContent(
                modifier = Modifier.padding(innerPadding),
                name = viewModel.name,
                onNameChanged = { viewModel.onNameChanged(it) },
                password = viewModel.password,
                onPasswordChanged = { viewModel.onPasswordChanged(it) },
                showPassword = viewModel.showPassword,
                onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() },
                onSignUpClick = navigationToSignUp,
                onSignInClick = { viewModel.login() },
            )
        }
    )
}

@Composable
private fun SignInContent(
    modifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 35.dp)
    ) {

        InputUserNameSection(
            name = name,
            onNameChanged = onNameChanged
        )

        Spacer(modifier = Modifier.height(6.dp))

        InputPasswordSection(
            password = password,
            onPasswordChanged = onPasswordChanged,
            showPassword = showPassword,
            onTogglePasswordVisibility = onTogglePasswordVisibility
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { onSignInClick() },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.login),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.find_id),
                color = Color.White
            )
            Text(
                text = stringResource(R.string.pipe_character),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(R.string.reset_password),
                color = Color.White
            )
            Text(
                text = stringResource(R.string.pipe_character),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(R.string.sign_up),
                color = Color.White,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }
}

@Preview
@Composable
private fun PreViewSignInScreen() {
    ANDANDROIDTheme {
        SignInScreen()
    }
}
