package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        hobby: String,
    ): Result<Unit> =
        userRepository.registerUser(
            User(
                name = userName,
                password = password,
                hobby = hobby
            )
        )

}
