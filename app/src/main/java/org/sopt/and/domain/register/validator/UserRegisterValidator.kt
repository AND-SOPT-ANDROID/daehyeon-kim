package org.sopt.and.domain.register.validator

class UserRegisterValidator {

    private val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

    fun isEmailValid(email: String): Boolean = emailRegex.matches(email)

    fun isPasswordValid(password: String): Boolean {
        if (password.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH)
            return false

        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { "!@#$%^&*()-_=+[]{}|;:'\",.<>?/".contains(it) }
        val criteriaChecks = listOf(hasUppercase, hasLowercase, hasDigit, hasSpecialChar)

        return criteriaChecks.count { it } >= MIN_VALID_CRITERIA
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 20
        private const val MIN_VALID_CRITERIA = 3
    }
}
