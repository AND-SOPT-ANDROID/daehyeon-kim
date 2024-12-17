package org.sopt.and.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.presentation.signup.component.SignUpBottomBar
import org.sopt.and.presentation.signup.component.SignUpTopBar
import org.sopt.and.presentation.signup.component.section.InputHobbySection
import org.sopt.and.presentation.signup.component.section.InputPasswordSection
import org.sopt.and.presentation.signup.component.section.InputUserNameSection

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigationToSignIn: () -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.uiSideEffect, lifecycleOwner) {
        viewModel.uiSideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpContract.SideEffect.NavigateToSignIn -> {
                        navigationToSignIn()
                    }

                    is SignUpContract.SideEffect.ShowSnackBar -> {
                        snackbarHostState.showSnackbar(sideEffect.message)
                    }
                }
            }
    }

    SignUpScreen(
        userName = uiState.user.name,
        password = uiState.user.password,
        hobby = uiState.user.hobby,
        showPassword = uiState.showPassword,
        isSignUpButtonEnabled = uiState.signUpButtonEnabled,
        onUserNameChanged = { name ->
            viewModel.setEvent(SignUpContract.Event.OnUserNameChanged(name))
        },
        onPasswordChanged = { password ->
            viewModel.setEvent(SignUpContract.Event.OnUserPasswordChanged(password))
        },
        onHobbyChanged = { hobby ->
            viewModel.setEvent(SignUpContract.Event.OnUserHobbyChanged(hobby))
        },
        onTogglePasswordVisibility = {
            viewModel.setEvent(SignUpContract.Event.TogglePasswordVisibility)
        },
        onSignUpClick = {
            viewModel.setEvent(SignUpContract.Event.OnSignUpClick)
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun SignUpScreen(
    userName: String,
    password: String,
    hobby: String,
    showPassword: Boolean,
    isSignUpButtonEnabled: Boolean,
    snackbarHostState: SnackbarHostState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onHobbyChanged: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSignUpClick: () -> Unit,
) {

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = { SignUpTopBar() },
        bottomBar = {
            SignUpBottomBar(
                isSignUpButtonEnabled = isSignUpButtonEnabled,
                onSignUpClick = onSignUpClick,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black,
        content = { padding ->
            SignUpContent(
                modifier = Modifier.padding(padding),
                userName = userName,
                password = password,
                hobby = hobby,
                showPassword = showPassword,
                onUserNameChanged = onUserNameChanged,
                onPasswordChanged = onPasswordChanged,
                onHobbyChanged = onHobbyChanged,
                onTogglePasswordVisibility = onTogglePasswordVisibility,
            )
        }
    )
}

@Composable
private fun SignUpContent(
    modifier: Modifier,
    userName: String,
    password: String,
    hobby: String,
    showPassword: Boolean,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onHobbyChanged: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit
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
        SignUpScreen(
            userName = "",
            password = "",
            hobby = "",
            showPassword = false,
            onUserNameChanged = {},
            onPasswordChanged = {},
            onHobbyChanged = {},
            onTogglePasswordVisibility = {},
            isSignUpButtonEnabled = false,
            onSignUpClick = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
