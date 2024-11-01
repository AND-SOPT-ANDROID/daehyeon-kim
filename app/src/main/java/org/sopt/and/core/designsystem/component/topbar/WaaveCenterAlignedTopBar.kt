package org.sopt.and.core.designsystem.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaaveCenterAlignedTopBar(
    titleText: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleText,
                color = Color.White
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(Color.Black)
    )
}

@Preview
@Composable
private fun PreviewWaaveCenterAlignedTopBar() {
    WaaveCenterAlignedTopBar(
        titleText = "waave"
    )
}
