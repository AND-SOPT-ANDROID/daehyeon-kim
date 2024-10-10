package org.sopt.and.util

fun isEmailValid(email: String): Boolean =
    "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex().matches(email)

fun isPasswordValid(password: String): Boolean {
    val minLength = password.length in 8..20
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { "!@#$%^&*()-_=+[]{}|;:'\",.<>?/".contains(it) }

    return minLength && listOf(hasUppercase, hasLowercase, hasDigit, hasSpecialChar).count { it } >= 3
}

fun isButtonEnabled(email: String, password: String): Boolean =
    email.isNotEmpty() && password.length >= 8
