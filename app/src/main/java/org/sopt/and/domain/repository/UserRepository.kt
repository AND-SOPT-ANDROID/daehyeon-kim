package org.sopt.and.domain.repository

import org.sopt.and.domain.model.Login
import org.sopt.and.domain.model.User

interface UserRepository {

    suspend fun registerUser(user: User): Result<Unit>

    suspend fun login(login: Login): Result<Unit>

    suspend fun fetchUserHobby(): Result<String>

}
