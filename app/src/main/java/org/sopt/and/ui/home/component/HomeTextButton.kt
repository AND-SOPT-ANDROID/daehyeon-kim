package org.sopt.and.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeTextButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .clickable { onClick() }
    )
}

@Preview
@Composable
private fun PreviewHomeTextButton() {
    HomeTextButton(
        text = "아아아",
        onClick = {}
    )
}
