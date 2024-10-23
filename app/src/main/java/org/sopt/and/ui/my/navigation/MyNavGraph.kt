package org.sopt.and.ui.my.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.ui.my.MyScreen

fun NavHostController.navigateToMy(navOptions: NavOptions) =
    navigate(MainTabRoute.My, navOptions)

fun NavGraphBuilder.myNavGraph() {
    composable<MainTabRoute.My> {
        MyScreen()
    }
}
