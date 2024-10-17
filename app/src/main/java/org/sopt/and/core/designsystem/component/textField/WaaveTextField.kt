package org.sopt.and.core.designsystem.component.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WaaveTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderValue: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderValue,
                color = Color.LightGray
            )
        },
        trailingIcon = trailingIcon?.let {
            @Composable { it() }
        },
        isError = isError,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.Gray,
            errorContainerColor = Color.Gray,
            errorTextColor = Color.White,
            errorIndicatorColor = Color.Red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun PreviewWaaveTextField() {
    WaaveTextField(
        value = "",
        onValueChange = {},
        placeholderValue = "textfield",
        trailingIcon = {
            TextButton(onClick = {}) {
                Text(
                    text = "show",
                    color = Color.White
                )
            }
        }
    )
}
