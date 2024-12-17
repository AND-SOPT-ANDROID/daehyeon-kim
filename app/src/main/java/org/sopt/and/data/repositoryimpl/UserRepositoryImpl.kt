package org.sopt.and.data.repositoryimpl

import kotlinx.coroutines.flow.firstOrNull
import org.sopt.and.core.network.FailureResponse
import org.sopt.and.data.datasource.local.TokenLocalDataSource
import org.sopt.and.data.datasource.remote.UserRemoteDataSource
import org.sopt.and.data.mapper.LoginMapper
import org.sopt.and.data.mapper.UserMapper
import org.sopt.and.domain.model.Login
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.data.util.error.HobbyError
import org.sopt.and.data.util.error.LoginError
import org.sopt.and.data.util.error.RegisterError
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource,
) : UserRepository {

    override suspend fun registerUser(user: User): Result<Unit> =
        runCatching {
            val requestDto = UserMapper.mapToRequestUserDto(user)
            val response = userRemoteDataSource.registerUser(requestDto)

            if (response.isSuccessful) {
                response.body()?.result?.registerNumber
                    ?: throw RegisterError.UnknownError("회원번호가 반환되지 않았습니다.")
            } else {
                throw when (response.code()) {
                    400 -> {
                        val errorCode = response.errorBody()?.string()
                        val errorResponse = FailureResponse(errorCode ?: "")
                        when (errorResponse.code) {
                            "00" -> RegisterError.InvalidRequest("요청 본문이 유효하지 않습니다.")
                            "01" -> RegisterError.InvalidLength("userName, password, hobby는 8자를 초과할 수 없습니다.")
                            else -> RegisterError.UnknownError("알 수 없는 400 오류가 발생했습니다.")
                        }
                    }

                    404 -> RegisterError.InvalidPath("유효하지 않은 경로로 요청이 들어왔습니다. 경로와 메소드를 확인하세요.")
                    409 -> RegisterError.DuplicateUserName("이미 존재하는 사용자 이름입니다.")
                    else -> RegisterError.UnknownError("알 수 없는 오류가 발생했습니다. 상태 코드: ${response.code()}")
                }
            }
        }

    override suspend fun login(login: Login): Result<Unit> =
        runCatching {
            val requestDto = LoginMapper.mapToRequestLoginDto(login)
            val response = userRemoteDataSource.login(requestDto)

            if (response.isSuccessful) {
                val token =
                    response.body()?.result?.token
                        ?: throw LoginError.UnknownError("토큰이 반환되지 않았습니다.")

                tokenLocalDataSource.saveToken(token)
                Result.success(Unit)
            } else {
                throw when (response.code()) {
                    400 -> {
                        val errorCode = response.errorBody()?.string()
                        val errorResponse = FailureResponse(errorCode ?: "")
                        when (errorResponse.code) {
                            "01" -> LoginError.InvalidRequest("요청 본문이 유효하지 않습니다.")
                            "02" -> LoginError.InvalidLoginRequest("잘못된 로그인 요청입니다. 비밀번호를 확인하세요.")
                            else -> LoginError.UnknownError("알 수 없는 400 오류가 발생했습니다.")
                        }
                    }

                    403 -> {
                        val errorCode = response.errorBody()?.string()
                        val errorResponse = FailureResponse(errorCode ?: "")
                        when (errorResponse.code) {
                            "01" -> LoginError.IncorrectPassword("비밀번호가 틀렸습니다.")
                            else -> LoginError.UnknownError("알 수 없는 403 오류가 발생했습니다.")
                        }
                    }

                    404 -> LoginError.InvalidPath("유효하지 않은 경로로 요청이 들어왔습니다. 경로와 메소드를 확인하세요.")
                    else -> LoginError.UnknownError("알 수 없는 오류가 발생했습니다. 상태 코드: ${response.code()}")
                }
            }
        }

    override suspend fun fetchUserHobby(): Result<String> =
        runCatching {
            val token = tokenLocalDataSource.getToken().firstOrNull()
                ?: throw HobbyError.MissingToken("저장된 토큰값이 없습니다.")
            val response = userRemoteDataSource.fetchUserHobby(token)

            if (response.isSuccessful) {
                response.body()?.result?.hobby
                    ?: throw HobbyError.UnknownError("취미 정보가 반환되지 않았습니다.")
            } else {
                throw when (response.code()) {
                    401 -> HobbyError.MissingToken("토큰이 없습니다.")
                    403 -> HobbyError.InvalidToken("유효하지 않은 토큰입니다.")
                    404 -> HobbyError.InvalidPath("유효하지 않은 경로로 요청이 들어왔습니다. 경로와 메소드를 확인하세요.")
                    else -> HobbyError.UnknownError("알 수 없는 오류가 발생했습니다. 상태 코드: ${response.code()}")
                }
            }
        }

}
