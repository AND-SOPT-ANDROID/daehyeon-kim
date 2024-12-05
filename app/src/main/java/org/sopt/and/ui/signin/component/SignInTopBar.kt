package org.sopt.and.ui.signin.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.topbar.WaaveCenterAlignedTopBar

@Composable
fun SignInTopBar() {
    WaaveCenterAlignedTopBar(
        titleText = stringResource(R.string.waave),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name,
                    tint = Color.White
                )
            }
        },
    )
}
