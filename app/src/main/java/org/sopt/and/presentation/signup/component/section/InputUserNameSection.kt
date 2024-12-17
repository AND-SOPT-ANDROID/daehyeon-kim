package org.sopt.and.presentation.signup.component.section

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.core.designsystem.component.textfield.WaaveTextField
import org.sopt.and.presentation.signup.component.item.InfoText

@Composable
fun InputUserNameSection(
    userName: String,
    onUserNameChanged: (String) -> Unit
) {
    WaaveTextField(
        value = userName,
        onValueChange = onUserNameChanged,
        placeholderValue = "닉네임을 입력해주세요.",
    )

    Spacer(modifier = Modifier.padding(5.dp))

    InfoText(text = "닉네임은 1 ~ 8 글자 이내로 입력해 주세요.")
}
