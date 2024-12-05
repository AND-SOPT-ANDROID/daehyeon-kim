package org.sopt.and.ui.signup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun SignUpBottomBar(
    isSignUpButtonEnabled: Boolean,
    onSignUpClick: () -> Unit = {}
) {
    BottomAppBar(
        containerColor = if (isSignUpButtonEnabled) Color.Blue else Color.Gray,
        windowInsets = BottomAppBarDefaults.windowInsets,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = isSignUpButtonEnabled) {
                    onSignUpClick()
                }
        ) {
            Text(
                text = stringResource(R.string.sign_up_btn),
                color = Color.White
            )
        }
    }
}
