package org.sopt.and.ui.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.component.textField.WaaveTextField
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInScreen(
    onSignInClick: (String, String, (String) -> Unit) -> Unit = { _, _, _ -> },
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
        content = { padding ->
            SignInContent(
                padding = padding,
                email = email,
                onEmailChanged = { email = it },
                password = password,
                onPasswordChanged = { password = it },
                onSignInClick = {
                    onSignInClick(email, password) { message ->
                        scope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                },
                onSignUpClick = { onSignUpClick() }
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
                text = "Wavve",
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black)
    )
}

@Composable
fun SignInContent(
    padding: PaddingValues,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit,
) {

    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 15.dp)
            .padding(top = 45.dp)
    ) {

        WaaveTextField(
            value = email,
            onValueChange = onEmailChanged,
            placeholderValue = "이메일 주소 또는 아이디"
        )

        Spacer(modifier = Modifier.padding(3.dp))

        WaaveTextField(
            value = password,
            onValueChange = onPasswordChanged,
            placeholderValue = "비밀번호",
            trailingIcon = {
                TextButton(
                    onClick = { showPassword = !showPassword }
                ) {
                    Text(
                        text = if (showPassword) "hide" else "show",
                        color = Color.White
                    )
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.padding(15.dp))

        Button(
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "로그인",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "아이디 찾기",
                color = Color.White
            )
            Text(
                text = "ㅣ",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "비밀번호 재설정",
                color = Color.White
            )
            Text(
                text = "ㅣ",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "회원가입",
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
