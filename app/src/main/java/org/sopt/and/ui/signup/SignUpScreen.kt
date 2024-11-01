package org.sopt.and.ui.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import org.sopt.and.ui.signup.component.InfoText

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(),
    navigationToSignIn: (UserInfo) -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val userInfo by viewModel.userInfo
    val snackbarMessage by viewModel.snackbarMessage

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
                userInfo = userInfo,
                isSignUpButtonEnabled = viewModel.isSignUpButtonEnabled(),
                onSignUpClick = {
                    viewModel.validateAndHandleEmailPassword { validUserInfo ->
                        navigationToSignIn(validUserInfo)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black,
        content = { padding ->
            SignUpContent(
                modifier = Modifier.padding(padding),
                email = userInfo.email,
                onEmailChanged = { viewModel.onEmailChanged(it) },
                password = userInfo.password,
                onPasswordChanged = { viewModel.onPasswordChanged(it) },
                showPassword = viewModel.showPassword.value,
                onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() },
                isEmailValid = viewModel.isEmailValid.value,
                isPasswordValid = viewModel.isPasswordValid.value
            )
        }
    )
}

@Composable
private fun SignUpTopBar() {
    WaaveCenterAlignedTopBar(
        titleText = stringResource(R.string.sign_up),
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = Icons.Default.Clear.name,
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
private fun SignUpBottomBar(
    userInfo: UserInfo,
    isSignUpButtonEnabled: Boolean,
    onSignUpClick: (UserInfo) -> Unit = {}
) {
    BottomAppBar(
        containerColor = if (isSignUpButtonEnabled) Color.Blue else Color.Gray,
        windowInsets = BottomAppBarDefaults.windowInsets,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = isSignUpButtonEnabled) {
                    onSignUpClick(userInfo)
                }
        ) {
            Text(
                text = stringResource(R.string.sign_up_btn),
                color = Color.White
            )
        }
    }
}

@Composable
private fun SignUpContent(
    modifier: Modifier,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    isEmailValid: Boolean,
    isPasswordValid: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 25.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_message),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.padding(12.dp))

        WaaveTextField(
            value = email,
            onValueChange = onEmailChanged,
            placeholderValue = stringResource(R.string.email_placeholder),
            isError = !isEmailValid
        )

        Spacer(modifier = Modifier.padding(5.dp))

        InfoText(text = stringResource(R.string.email_info))

        Spacer(modifier = Modifier.padding(12.dp))

        WaaveTextField(
            value = password,
            onValueChange = onPasswordChanged,
            placeholderValue = stringResource(R.string.password_placeholder),
            trailingIcon = {
                TextButton(
                    onClick = onTogglePasswordVisibility
                ) {
                    Text(
                        text = if (showPassword) stringResource(R.string.password_hide) else stringResource(R.string.password_show),
                        color = Color.White
                    )
                }
            },
            isError = !isPasswordValid,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(5.dp))

        InfoText(text = stringResource(R.string.password_info))
    }
}

@Preview
@Composable
private fun PreViewSignUpScreen() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}
