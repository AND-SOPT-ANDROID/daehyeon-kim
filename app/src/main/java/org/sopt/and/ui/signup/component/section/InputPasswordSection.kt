package org.sopt.and.ui.signup.component.section

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.textfield.WaaveTextField
import org.sopt.and.ui.signup.component.item.InfoText

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
        placeholderValue = stringResource(R.string.password_placeholder),
        trailingIcon = {
            TextButton(
                onClick = onTogglePasswordVisibility
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

    Spacer(modifier = Modifier.padding(5.dp))

    InfoText(text = stringResource(R.string.password_info))
}
