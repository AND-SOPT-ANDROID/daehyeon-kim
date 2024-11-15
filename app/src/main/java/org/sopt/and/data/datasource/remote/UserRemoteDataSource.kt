package org.sopt.and.data.datasource.remote

import org.sopt.and.core.network.SuccessResponse
import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.data.dto.request.RequestRegisterUserDto
import org.sopt.and.data.dto.response.ResponseHobbyDto
import org.sopt.and.data.dto.response.ResponseLoginDto
import org.sopt.and.data.dto.response.ResponseRegisterUserDto
import org.sopt.and.data.service.UserService
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
) {

    suspend fun registerUser(requestRegisterUserDto: RequestRegisterUserDto): Response<SuccessResponse<ResponseRegisterUserDto>> =
        userService.registerUser(requestRegisterUserDto)

    suspend fun login(requestLoginDto: RequestLoginDto): Response<SuccessResponse<ResponseLoginDto>> =
        userService.login(requestLoginDto)

    suspend fun fetchUserHobby(token: String): Response<SuccessResponse<ResponseHobbyDto>> =
        userService.fetchUserHobby(token)

}

