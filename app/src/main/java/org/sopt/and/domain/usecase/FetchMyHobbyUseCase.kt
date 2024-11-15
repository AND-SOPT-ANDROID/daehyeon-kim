package org.sopt.and.domain.usecase

import org.sopt.and.data.repositoryimpl.UserRepositoryImpl
import javax.inject.Inject

class FetchMyHobbyUseCase @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) {

    suspend operator fun invoke(): Result<String> =
        userRepositoryImpl.fetchUserHobby()
}
