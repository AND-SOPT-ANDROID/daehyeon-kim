package org.sopt.and.presentation.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.presentation.signin.SignInRoute

fun NavGraphBuilder.signInNavGraph(
    navigationToSignUp: () -> Unit = {},
    navigationToHome: () -> Unit = {}
) {
    composable<Route.SignIn> {
        SignInRoute(
            navigationToSignUp = navigationToSignUp,
            navigationToHome = navigationToHome
        )
    }
}
