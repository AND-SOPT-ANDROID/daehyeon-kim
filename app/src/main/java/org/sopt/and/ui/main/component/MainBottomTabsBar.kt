package org.sopt.and.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.main.MainTab
import org.sopt.and.ui.main.rememberMainNavigator

@Composable
fun MainBottomTabsBar(
    mainTabs: List<MainTab>,
    currentBottomTab: MainTab?,
    onTabClicked: (MainTab) -> Unit,
) {
    NavigationBar(
        containerColor = Color.Black
    ) {
        mainTabs.forEach { tab ->
            NavigationBarItem(
                selected = currentBottomTab == tab,
                onClick = { onTabClicked(tab) },
                icon = {
                    Image(
                        painter = painterResource(tab.iconResId),
                        contentDescription = tab.contentDescription,
                        modifier = Modifier
                            .size(24.dp),
                    )
                },
                label = {
                    Text(
                        text = tab.contentDescription,
                        color = Color.White,
                        fontSize = 10.sp,
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMyBottomTabsBar() {
    val navigator = rememberMainNavigator()
    MainBottomTabsBar(
        mainTabs = MainTab.entries,
        currentBottomTab = navigator.currentTab,
        onTabClicked = { tab -> navigator.navigateMainTab(tab) }
    )
}
