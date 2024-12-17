package org.sopt.and.presentation.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.presentation.signup.SignUpRoute

fun NavGraphBuilder.signUpNavGraph(
    navigationToSignIn: () -> Unit = {}
) {
    composable<Route.SignUp> {
        SignUpRoute(
            navigationToSignIn = navigationToSignIn
        )
    }
}
