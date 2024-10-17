package org.sopt.and.ui.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import org.sopt.and.ui.component.textField.WaaveTextField
import org.sopt.and.ui.signup.component.InfoText
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.util.isButtonEnabled
import org.sopt.and.util.isEmailValid
import org.sopt.and.util.isPasswordValid

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(),
    onSignUpClick: (String, String) -> Unit = { _, _ ->}
) {
    val email by viewModel.email
    val password by viewModel.password
    val showPassword by viewModel.showPassword

    Scaffold(
        topBar = { SignUpTopBar() },
        bottomBar = {
            SignUpBottomBar(
                isEnabled = isButtonEnabled(email, password),
                onSignUpClick = { onSignUpClick(email, password) }
            )
        },
        containerColor = Color.Black,
        content = { padding ->
            SignUpContent(
                padding = padding,
                email = email,
                onEmailChanged = { viewModel.onEmailChanged(it) },
                password = password,
                onPasswordChanged = { viewModel.onPasswordChanged(it) },
                showPassword = showPassword,
                onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.sign_up),
                color = Color.White,
                fontSize = 18.sp
            )
        },
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
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black)
    )
}

@Composable
private fun SignUpBottomBar(
    isEnabled: Boolean,
    onSignUpClick: () -> Unit = {}
) {
    BottomAppBar(
        containerColor = if (isEnabled) Color.Blue else Color.Gray,
        windowInsets = BottomAppBarDefaults.windowInsets,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = isEnabled) { onSignUpClick() }
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
    padding: PaddingValues,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
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
            isError = email.isNotEmpty() && !isEmailValid(email)
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
            isError = password.isNotEmpty() && !isPasswordValid(password),
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
