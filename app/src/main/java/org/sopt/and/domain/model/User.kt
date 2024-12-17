package org.sopt.and.domain.model

data class User(
    val name: String,
    val password: String,
    val hobby: String,
) {
    fun isNotBlank(): Boolean =
        name.isNotBlank() && password.isNotBlank() && hobby.isNotBlank()
}
