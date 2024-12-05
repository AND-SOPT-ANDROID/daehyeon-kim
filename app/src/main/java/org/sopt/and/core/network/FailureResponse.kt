package org.sopt.and.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(
    @SerialName("code")
    val code: String
)
