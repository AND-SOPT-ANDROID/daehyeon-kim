package org.sopt.and.presentation.signup.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.topbar.WaaveCenterAlignedTopBar

@Composable
fun SignUpTopBar() {
    WaaveCenterAlignedTopBar(
        titleText = stringResource(R.string.sign_up),
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = Icons.Default.Clear.name,
                    tint = Color.White
                )
            }
        }
    )
}
