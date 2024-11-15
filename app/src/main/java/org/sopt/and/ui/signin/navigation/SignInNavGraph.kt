package org.sopt.and.ui.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.ui.signin.SignInScreen

fun NavGraphBuilder.signInNavGraph(
    navigationToSignUp: () -> Unit = {},
    navigationToHome: () -> Unit = {}
) {
    composable<Route.SignIn> {
        SignInScreen(
            navigationToSignUp = navigationToSignUp,
            navigationToHome = navigationToHome
        )
    }
}
