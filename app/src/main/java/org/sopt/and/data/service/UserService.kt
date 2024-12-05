package org.sopt.and.data.service

import org.sopt.and.core.network.SuccessResponse
import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.data.dto.request.RequestRegisterUserDto
import org.sopt.and.data.dto.response.ResponseHobbyDto
import org.sopt.and.data.dto.response.ResponseLoginDto
import org.sopt.and.data.dto.response.ResponseRegisterUserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/user")
    suspend fun registerUser(
        @Body requestRegisterUserDto: RequestRegisterUserDto
    ): Response<SuccessResponse<ResponseRegisterUserDto>>

    @POST("/login")
    suspend fun login(
        @Body requestLoginDto: RequestLoginDto
    ): Response<SuccessResponse<ResponseLoginDto>>

    @GET("/user/my-hobby")
    suspend fun fetchUserHobby(
        @Header("token") token: String
    ): Response<SuccessResponse<ResponseHobbyDto>>

}

