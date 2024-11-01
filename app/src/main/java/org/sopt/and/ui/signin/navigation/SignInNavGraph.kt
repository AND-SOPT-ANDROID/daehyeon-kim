package org.sopt.and.ui.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.and.core.navigation.Route
import org.sopt.and.model.UserInfo
import org.sopt.and.ui.signin.SignInScreen

fun NavGraphBuilder.signInNavGraph(
    navigationToSignUp: () -> Unit = {},
    navigationToHome:() -> Unit = {}
) {
    composable<Route.SignIn> { backStackEntry ->
        val item = backStackEntry.toRoute<Route.SignIn>()
        val userInfo = UserInfo(item.email, item.password)
        SignInScreen(
            userInfo = userInfo,
            navigationToSignUp = navigationToSignUp,
            navigationToHome = navigationToHome
        )
    }
}
