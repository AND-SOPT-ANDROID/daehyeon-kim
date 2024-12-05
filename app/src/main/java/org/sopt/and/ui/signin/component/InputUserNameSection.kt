package org.sopt.and.ui.signin.component

import androidx.compose.runtime.Composable
import org.sopt.and.core.designsystem.component.textfield.WaaveTextField

@Composable
fun InputUserNameSection(
    name: String,
    onNameChanged: (String) -> Unit
) {
    WaaveTextField(
        value = name,
        onValueChange = onNameChanged,
        placeholderValue = "이름"
    )
}
