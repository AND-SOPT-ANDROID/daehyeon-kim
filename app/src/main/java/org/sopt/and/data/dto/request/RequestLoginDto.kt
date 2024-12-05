package org.sopt.and.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerialName("username")
    val name: String,
    @SerialName("password")
    val password: String
)
