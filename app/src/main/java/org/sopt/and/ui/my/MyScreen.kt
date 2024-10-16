package org.sopt.and.ui.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun MyScreen(
    userName: String = ""
) {
    Scaffold(
        topBar = { MyScreenTopBar(userName) },
        containerColor = Color.Black,
        content = { padding ->
            MyScreenContent(
                padding = padding
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreenTopBar(
    userName: String
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),

            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = Icons.Default.AccountCircle.name,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = userName,
                    color = Color.White
                )
            }
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = Icons.Outlined.Notifications.name,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = Icons.Outlined.Settings.name,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.DarkGray)
    )
}

@Composable
fun MyScreenContent(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.first_payment_message),
                color = Color.Gray
            )
            Text(
                text = stringResource(R.string.purchase),
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.no_subscription_message),
                color = Color.Gray
            )
            Text(
                text = stringResource(R.string.purchase),
                color = Color.White
            )
        }


        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.watching_history),
                color = Color.White
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = Icons.Outlined.Info.name,
                    tint = Color.Gray
                )
                Text(
                    text = stringResource(R.string.watching_history_empty),
                    color = Color.Gray
                )
            }
            Text(
                text = stringResource(R.string.favorite_programs),
                color = Color.White
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = Icons.Outlined.Info.name,
                    tint = Color.Gray
                )
                Text(
                    text = stringResource(R.string.favorite_programs_empty),
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMyScreen() {
    ANDANDROIDTheme {
        MyScreen(
            userName = "name"
        )
    }
}
