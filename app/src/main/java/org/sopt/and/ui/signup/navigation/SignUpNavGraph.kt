package org.sopt.and.ui.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.ui.signup.SignUpScreen

fun NavGraphBuilder.signUpNavGraph(
    navigationToSignIn: () -> Unit = {}
) {
    composable<Route.SignUp> {
        SignUpScreen(
            navigationToSignIn = navigationToSignIn
        )
    }
}
