package org.sopt.and.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import org.sopt.and.ui.home.navigation.homeNavGraph
import org.sopt.and.ui.main.component.MainBottomTabsBar
import org.sopt.and.ui.my.navigation.myNavGraph
import org.sopt.and.ui.search.navigation.searchNavGraph
import org.sopt.and.ui.signin.navigation.signInNavGraph
import org.sopt.and.ui.signup.navigation.signUpNavGraph

@Composable
fun MainScreen() {
    val navigator = rememberMainNavigator()

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = navigator.shouldShowBottomBar(),
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
            ) {
                MainBottomTabsBar(
                    mainTabs = MainTab.entries,
                    currentBottomTab = navigator.currentTab,
                    onTabClicked = { tab -> navigator.navigateMainTab(tab) }
                )
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        containerColor = Color.Black,
        content = { innerPadding ->
            MainContent(
                padding = innerPadding,
                navigator = navigator
            )
        }
    )
}

@Composable
private fun MainContent(
    padding: PaddingValues,
    navigator: MainNavigator
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            signInNavGraph(
                navigationToSignUp = { navigator.navigationToSignUp() },
                navigationToHome = { navigator.navigationToHome() }
            )

            signUpNavGraph(
                navigationToSignIn = { userInfo -> navigator.navigationToSignIn(userInfo) }
            )

            homeNavGraph(padding)
            myNavGraph()
            searchNavGraph()
        }
    }
}
