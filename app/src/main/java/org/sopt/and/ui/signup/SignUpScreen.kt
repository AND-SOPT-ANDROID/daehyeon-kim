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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.component.textField.WaaveTextField
import org.sopt.and.ui.signup.component.InfoText
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.util.isButtonEnabled
import org.sopt.and.util.isEmailValid
import org.sopt.and.util.isPasswordValid

@Composable
fun SignUpScreen(
    onSignUpClick: (String, String) -> Unit = { _, _ ->}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                onEmailChanged = { email = it },
                password = password,
                onPasswordChanged = { password = it },
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
                text = "회원가입",
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
                    contentDescription = "",
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
                text = "Wavve 회원가입",
                color = Color.White
            )
        }
    }
}

@Composable
fun SignUpContent(
    padding: PaddingValues,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(vertical = 15.dp, horizontal = 25.dp)
    ) {
        Text(
            text = "이메일과 비밀번호만으로" +
                    "\nWavve를 즐길 수 있어요!",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.padding(12.dp))

        WaaveTextField(
            value = email,
            onValueChange = onEmailChanged,
            placeholderValue = "Wavve@example.com",
            isError = email.isNotEmpty() && !isEmailValid(email)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        InfoText("로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.")

        Spacer(modifier = Modifier.padding(12.dp))

        WaaveTextField(
            value = password,
            onValueChange = onPasswordChanged,
            placeholderValue = "Wavve 비밀번호 설정",
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
            isError = password.isNotEmpty() && !isPasswordValid(password),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(5.dp))

        InfoText("비밀번호는 8 ~ 20자 이내로 영문, 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.")
    }
}

@Preview
@Composable
private fun PreViewSignUpScreen() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}
