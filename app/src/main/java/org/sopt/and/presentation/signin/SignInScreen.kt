package org.sopt.and.presentation.signin

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.presentation.signin.component.InputPasswordSection
import org.sopt.and.presentation.signin.component.InputUserNameSection
import org.sopt.and.presentation.signin.component.SignInTopBar

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navigationToHome: () -> Unit = {},
    navigationToSignUp: () -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.uiSideEffect, lifecycleOwner) {
        viewModel.uiSideEffect
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignInContract.SideEffect.NavigateToHome -> navigationToHome()

                    is SignInContract.SideEffect.ShowSnackBar -> {
                        snackbarHostState.showSnackbar(sideEffect.message)
                    }
                }
            }
    }

    SignInScreen(
        name = uiState.login.name,
        password = uiState.login.password,
        showPassword = uiState.showPassword,
        onNameChanged = { name ->
            viewModel.setEvent(SignInContract.Event.OnUserNameChanged(name))
        },
        onPasswordChanged = { password ->
            viewModel.setEvent(SignInContract.Event.OnUserPasswordChanged(password))
        },
        onTogglePasswordVisibility = {
            viewModel.setEvent(SignInContract.Event.TogglePasswordVisibility)
        },
        onSignInClick = {
            viewModel.setEvent(SignInContract.Event.OnSignInClick)
        },
        onSignUpClick = navigationToSignUp,
        snackbarHostState = snackbarHostState,
    )

}

@Composable
private fun SignInScreen(
    name: String,
    password: String,
    showPassword: Boolean,
    onNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = { SignInTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black,
        content = { innerPadding ->
            SignInContent(
                modifier = Modifier.padding(innerPadding),
                name = name,
                password = password,
                showPassword = showPassword,
                onNameChanged = onNameChanged,
                onPasswordChanged = onPasswordChanged,
                onTogglePasswordVisibility = onTogglePasswordVisibility,
                onSignUpClick = onSignUpClick,
                onSignInClick = onSignInClick,
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
        SignInScreen(
            name = "",
            password = "",
            showPassword = false,
            onNameChanged = { },
            onPasswordChanged = { },
            onTogglePasswordVisibility = { },
            onSignUpClick = { },
            onSignInClick = { },
            snackbarHostState = SnackbarHostState()
        )
    }
}
