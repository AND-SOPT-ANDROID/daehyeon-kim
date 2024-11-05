package org.sopt.and.ui.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.sopt.and.domain.register.usecase.ValidateUserRegisterUseCase
import org.sopt.and.domain.register.validator.UserRegisterValidator
import org.sopt.and.model.UserInfo

class SignUpViewModel(
    private val validator: UserRegisterValidator = UserRegisterValidator(),
    private val validateUserRegisterUseCase: ValidateUserRegisterUseCase = ValidateUserRegisterUseCase(validator)
) : ViewModel() {

    private val _userInfo = mutableStateOf(UserInfo())
    val userInfo: State<UserInfo> = _userInfo

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _snackbarMessage = mutableStateOf("")
    val snackbarMessage: State<String> = _snackbarMessage

    private val _isEmailValid = mutableStateOf(true)
    val isEmailValid: State<Boolean> = _isEmailValid

    private val _isPasswordValid = mutableStateOf(true)
    val isPasswordValid: State<Boolean> = _isPasswordValid

    fun onEmailChanged(newEmail: String) {
        _userInfo.value = _userInfo.value.copy(email = newEmail)
    }

    fun onPasswordChanged(newPassword: String) {
        _userInfo.value = _userInfo.value.copy(password = newPassword)
    }

    fun validateAndHandleEmailPassword(onValidationSuccess: (UserInfo) -> Unit) {
        val result = validateUserRegisterUseCase(
            email = _userInfo.value.email,
            password = _userInfo.value.password
        )

        _isEmailValid.value = result.isEmailValid
        _isPasswordValid.value = result.isPasswordValid

        if (_isEmailValid.value && _isPasswordValid.value) {
            onValidationSuccess(_userInfo.value)
        }
        handleValidationResult()
    }

    private fun handleValidationResult() {
        when {
            !_isEmailValid.value && !_isPasswordValid.value -> {
                _snackbarMessage.value = "잘못된 이메일, 비밀번호 형식입니다."
            }

            !_isEmailValid.value -> {
                _snackbarMessage.value = "잘못된 이메일 형식입니다."
            }

            !_isPasswordValid.value -> {
                _snackbarMessage.value = "잘못된 비밀번호 형식입니다."
            }
        }
    }

    fun isSignUpButtonEnabled(): Boolean =
        _userInfo.value.email.isNotBlank() && _userInfo.value.password.length >= 8

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

    fun togglePasswordVisibility() {
        _showPassword.value = !showPassword.value
    }

}
