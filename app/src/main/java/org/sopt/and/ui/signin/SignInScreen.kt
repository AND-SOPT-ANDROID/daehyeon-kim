package org.sopt.and.ui.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.model.SignInInfo
import org.sopt.and.ui.component.textField.WaaveTextField
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInScreen(
    onSignInClick: (SignInInfo, snackbarMessage: (String) -> Unit) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { SignInTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black,
        content = { innerPadding ->
            SignInContent(
                email = email,
                onEmailChanged = { email = it },
                password = password,
                onPasswordChanged = { password = it },
                onSignInClick = {
                    onSignInClick(SignInInfo(email, password)) { message ->
                        scope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                },
                onSignUpClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 15.dp, vertical = 35.dp)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.waave),
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black)
    )
}

@Composable
fun SignInContent(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit,
    modifier: Modifier
) {

    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
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
                    onClick = { showPassword = !showPassword }
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
            onClick = onSignInClick,
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
