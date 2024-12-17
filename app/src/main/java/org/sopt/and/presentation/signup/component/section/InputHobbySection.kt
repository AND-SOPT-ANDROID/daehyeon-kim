package org.sopt.and.presentation.signup.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.core.designsystem.component.textfield.WaaveTextField
import org.sopt.and.core.designsystem.theme.ANDANDROIDTheme
import org.sopt.and.presentation.signup.component.item.InfoText

@Composable
fun InputHobbySection(
    hobby: String,
    onHobbyChanged: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        WaaveTextField(
            value = hobby,
            onValueChange = onHobbyChanged,
            placeholderValue = "취미를 입력해주세요.",
        )
        InfoText(text = "취미는 1 ~ 8글자 이내로 입력해 주세요.")
    }
}

@Preview
@Composable
private fun PreviewInputHobbySection() {
    ANDANDROIDTheme {
        InputHobbySection(
            hobby = "",
            onHobbyChanged = {}
        )
    }
}
