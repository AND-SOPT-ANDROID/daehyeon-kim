package org.sopt.and.data.mapper

import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.domain.model.Login

object LoginMapper {

    fun mapToRequestLoginDto(login: Login) =
        RequestLoginDto(
            name = login.name,
            password = login.password
        )
}
