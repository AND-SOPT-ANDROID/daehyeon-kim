package org.sopt.and.domain.register.model

data class UserRegisterValidationResult(
    val isEmailValid: Boolean,
    val isPasswordValid: Boolean,
)
