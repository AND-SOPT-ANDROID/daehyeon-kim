package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object SignIn : Route

    @Serializable
    data object SignUp : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object My : MainTabRoute
}
