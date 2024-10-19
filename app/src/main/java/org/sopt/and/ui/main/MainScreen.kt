package org.sopt.and.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.core.navigation.My
import org.sopt.and.core.navigation.SignIn
import org.sopt.and.core.navigation.SignUp
import org.sopt.and.model.UserInfo
import org.sopt.and.ui.my.MyScreen
import org.sopt.and.ui.signin.SignInScreen
import org.sopt.and.ui.signup.SignUpScreen

@Composable
fun MainScreen() {
    MainContent()
}

@Composable
private fun MainContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SignUp
    ) {
        composable<SignUp> {
            SignUpScreen(
                navigationToSignIn = { userInfo ->
                    navController.navigate(SignIn(userInfo.email, userInfo.password)) {
                        popUpTo(SignUp) { inclusive = true }
                    }
                }
            )
        }

        composable<SignIn> { backStackEntry ->
            val item = backStackEntry.toRoute<SignIn>()
            SignInScreen(
                userInfo = UserInfo(item.email, item.password),
                navigationToSignUp = { navController.navigate(SignUp)},
                navigationToMy = { email ->
                    navController.navigate(My(email))
                }
            )
        }

        composable<My> { backStackEntry ->
            val item = backStackEntry.toRoute<My>()
            MyScreen(userName = item.email)
        }
    }
}
