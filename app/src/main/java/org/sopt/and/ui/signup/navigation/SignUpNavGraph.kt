package org.sopt.and.ui.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.model.UserInfo
import org.sopt.and.ui.signup.SignUpScreen

fun NavGraphBuilder.signUpNavGraph(
    navigationToSignIn: (userInfo: UserInfo) -> Unit = {}
) {
    composable<Route.SignUp> {
        SignUpScreen(
            navigationToSignIn = navigationToSignIn
        )
    }
}
