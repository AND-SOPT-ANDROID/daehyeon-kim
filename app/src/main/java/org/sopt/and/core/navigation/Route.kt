package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data class SignIn(
    val email: String,
    val password: String,
)

@Serializable
data object SignUp

@Serializable
data class My(
    val email: String
)
