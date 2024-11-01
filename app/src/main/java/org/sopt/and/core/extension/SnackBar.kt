package org.sopt.and.core.extension

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun Modifier.showSnackbarEffect(
    message: String,
    snackbarHostState: SnackbarHostState,
    onMessageShown: () -> Unit = {}
) = this.then(
    Modifier.apply {
        if (message.isNotEmpty()) {
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(message)
                onMessageShown()
            }
        }
    }
)
