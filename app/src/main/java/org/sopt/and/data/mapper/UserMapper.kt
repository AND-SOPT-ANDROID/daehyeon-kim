package org.sopt.and.data.mapper

import org.sopt.and.data.dto.request.RequestRegisterUserDto
import org.sopt.and.domain.model.User

object UserMapper {

    fun mapToRequestUserDto(user: User): RequestRegisterUserDto =
        RequestRegisterUserDto(
            userName = user.name,
            password = user.password,
            hobby = user.hobby
        )

}
