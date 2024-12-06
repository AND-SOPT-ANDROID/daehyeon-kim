package org.sopt.and.data.util.error

sealed class HobbyError : Exception() {
    data class MissingToken(override val message: String) : HobbyError()
    data class InvalidToken(override val message: String) : HobbyError()
    data class InvalidPath(override val message: String) : HobbyError()
    data class UnknownError(override val message: String) : HobbyError()
}
