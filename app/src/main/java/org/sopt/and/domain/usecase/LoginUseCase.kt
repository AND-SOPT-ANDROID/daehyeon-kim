package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.Login
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(name: String, password: String): Result<Unit> =
        userRepository.login(
            Login(
                name = name,
                password = password
            )
        )

}
