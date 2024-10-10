package org.sopt.and.ui.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoText(
    text: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            lineHeight = 15.sp
        )
    }
}

@Preview
@Composable
private fun PreviewInfoText() {
    InfoText("로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.")
}
