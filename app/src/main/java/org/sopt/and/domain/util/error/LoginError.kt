package org.sopt.and.domain.util.error

sealed class LoginError : Exception() {
    data class InvalidRequest(override val message: String) : LoginError()
    data class InvalidLoginRequest(override val message: String) : LoginError()
    data class IncorrectPassword(override val message: String) : LoginError()
    data class InvalidPath(override val message: String) : LoginError()
    data class UnknownError(override val message: String) : LoginError()
}
