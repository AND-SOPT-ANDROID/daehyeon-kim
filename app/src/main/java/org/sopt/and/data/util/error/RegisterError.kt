package org.sopt.and.data.util.error

sealed class RegisterError : Exception() {
    data class InvalidLength(override val message: String) : RegisterError()
    data class DuplicateUserName(override val message: String) : RegisterError()
    data class InvalidRequest(override val message: String) : RegisterError()
    data class InvalidPath(override val message: String) : HobbyError()
    data class UnknownError(override val message: String) : RegisterError()
}
