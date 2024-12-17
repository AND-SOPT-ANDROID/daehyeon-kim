package org.sopt.and.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import org.sopt.and.presentation.home.navigation.homeNavGraph
import org.sopt.and.presentation.main.component.MainBottomTabsBar
import org.sopt.and.presentation.my.navigation.myNavGraph
import org.sopt.and.presentation.search.navigation.searchNavGraph
import org.sopt.and.presentation.signin.navigation.signInNavGraph
import org.sopt.and.presentation.signup.navigation.signUpNavGraph

@Composable
fun MainScreen() {
    val navigator = rememberMainNavigator()

    Scaffold(
        bottomBar = {
            if (navigator.shouldShowBottomBar()) {
                MainBottomTabsBar(
                    mainTabs = MainTab.entries,
                    currentBottomTab = navigator.currentTab,
                    onTabClicked = navigator::navigateMainTab
                )
            }
        },
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
                navigationToSignUp = navigator::navigationToSignUp,
                navigationToHome = navigator::navigationToHome,
            )

            signUpNavGraph(
                navigationToSignIn = navigator::navigationToSignIn
            )

            homeNavGraph()
            myNavGraph()
            searchNavGraph()
        }
    }
}
