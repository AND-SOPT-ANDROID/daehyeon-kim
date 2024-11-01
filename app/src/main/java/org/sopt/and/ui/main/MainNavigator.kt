package org.sopt.and.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.core.navigation.Route
import org.sopt.and.model.UserInfo
import org.sopt.and.ui.home.navigation.navigateToHome
import org.sopt.and.ui.my.navigation.navigateToMy
import org.sopt.and.ui.search.navigation.navigateToSearch

class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination = Route.SignIn()

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    private val navOptions = navOptions {
        launchSingleTop = true
    }

    fun navigationToSignUp() = navController.navigate(Route.SignUp, navOptions)

    fun navigationToHome() = navController.navigate(MainTabRoute.Home) {
        popUpTo(Route.SignIn("","")) {
            inclusive = true
        }
    }

    fun navigationToSignIn(userInfo: UserInfo) =
        navController.navigate(Route.SignIn(userInfo.email, userInfo.password), navOptions)

    fun navigateMainTab(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }

        when (tab) {
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.MY -> navController.navigateToMy(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
