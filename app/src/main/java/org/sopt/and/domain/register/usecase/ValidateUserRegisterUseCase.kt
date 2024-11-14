package org.sopt.and.domain.register.usecase

import org.sopt.and.domain.register.model.UserRegisterValidationResult
import org.sopt.and.domain.register.validator.UserRegisterValidator

class ValidateUserRegisterUseCase(
    private val validator: UserRegisterValidator,
) {

    operator fun invoke(email: String, password: String): UserRegisterValidationResult {
        val isEmailValid = validator.isEmailValid(email)
        val isPasswordValid = validator.isPasswordValid(password)

        return UserRegisterValidationResult(isEmailValid, isPasswordValid)
    }

}
