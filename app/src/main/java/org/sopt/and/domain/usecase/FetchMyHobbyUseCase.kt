package org.sopt.and.domain.usecase

import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class FetchMyHobbyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<String> =
        userRepository.fetchUserHobby()
}
