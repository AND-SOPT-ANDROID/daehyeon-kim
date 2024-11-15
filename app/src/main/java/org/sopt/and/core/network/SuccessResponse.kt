package org.sopt.and.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T>(
    @SerialName("result")
    val result: T
)
