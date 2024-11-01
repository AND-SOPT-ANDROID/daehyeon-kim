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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.textfield.WaaveTextField
import org.sopt.and.core.designsystem.component.topbar.WaaveCenterAlignedTopBar
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.model.UserInfo

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = viewModel(),
    userInfo: UserInfo,
    navigationToHome: () -> Unit = {},
    navigationToSignUp: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage by viewModel.snackbarMessage
    val userEnteredInfo by viewModel.userEnteredInfo

    if (snackbarMessage.isNotEmpty()) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(snackbarMessage)
            viewModel.clearSnackbarMessage()
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
                userInfo = userInfo,
                email = userEnteredInfo.email,
                onEmailChanged = { viewModel.onEmailChanged(it) },
                password = userEnteredInfo.password,
                onPasswordChanged = { viewModel.onPasswordChanged(it) },
                showPassword = viewModel.showPassword.value,
                onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() },
                onSignUpClick = navigationToSignUp,
                onSignInClick = { userInfo ->
                    viewModel.validateSignInCredentials(
                        userInfo,
                        onLoginSuccess = navigationToHome
                    )
                },
            )
        }
    )
}

@Composable
private fun SignInTopBar() {
    WaaveCenterAlignedTopBar(
        titleText = stringResource(R.string.waave),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                    tint = Color.White
                )
            }
        },
    )
}

@Composable
private fun SignInContent(
    modifier: Modifier,
    userInfo: UserInfo,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: (UserInfo) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 35.dp)
    ) {
        WaaveTextField(
            value = email,
            onValueChange = onEmailChanged,
            placeholderValue = stringResource(R.string.email_or_id),
        )

        Spacer(modifier = Modifier.height(6.dp))

        WaaveTextField(
            value = password,
            onValueChange = onPasswordChanged,
            placeholderValue = stringResource(R.string.password),
            trailingIcon = {
                TextButton(
                    onClick = onTogglePasswordVisibility,
                ) {
                    Text(
                        text = if (showPassword) stringResource(R.string.password_hide) else stringResource(R.string.password_show),
                        color = Color.White
                    )
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { onSignInClick(userInfo) },
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
        SignInScreen(
            userInfo = UserInfo()
        )
    }
}
