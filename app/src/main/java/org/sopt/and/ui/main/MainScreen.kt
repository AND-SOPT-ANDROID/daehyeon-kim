package org.sopt.and.ui.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
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
            if (navigator.shouldShowBottomBar()) {
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
    Box (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            enterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            signInNavGraph(
                navigationToSignUp = { navigator.navigationToSignUp() },
                navigationToHome = { navigator.navigationToHome() }
            )

            signUpNavGraph(
                navigationToSignIn = { navigator.navigationToSignIn() }
            )

            homeNavGraph()
            myNavGraph()
            searchNavGraph()
        }
    }
}
