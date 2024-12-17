package org.sopt.and.presentation.signin.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.textfield.WaaveTextField

@Composable
fun InputPasswordSection(
    password: String,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
) {
    WaaveTextField(
        value = password,
        onValueChange = onPasswordChanged,
        placeholderValue = "비밀번호",
        trailingIcon = {
            TextButton(
                onClick = onTogglePasswordVisibility,
            ) {
                Text(
                    text = when (showPassword) {
                        true -> stringResource(R.string.password_hide)
                        false -> stringResource(R.string.password_show)
                    },
                    color = Color.White
                )
            }
        },
        visualTransformation = when (showPassword) {
            true -> VisualTransformation.None
            false -> PasswordVisualTransformation()
        }
    )
}
