package org.sopt.and.ui.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.sopt.and.model.UserInfo

class SignUpViewModel : ViewModel() {

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
        if (validateEmailAndPassword()) {
            handleValidationResult(onValidationSuccess)
        }
    }

    private fun validateEmailAndPassword(): Boolean {
        _isEmailValid.value = isEmailValid(_userInfo.value.email)
        _isPasswordValid.value = isPasswordValid(_userInfo.value.password)

        return _isEmailValid.value && _isPasswordValid.value
    }

    private fun handleValidationResult(onValidationSuccess: (UserInfo) -> Unit) {
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
            else -> {
                onValidationSuccess(_userInfo.value)
            }
        }
    }

    private val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    private fun isEmailValid(email: String): Boolean = emailRegex.matches(email)

    private fun isPasswordValid(password: String): Boolean {
        if (password.length !in 8..20) return false

        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { "!@#$%^&*()-_=+[]{}|;:'\",.<>?/".contains(it) }

        return listOf(hasUppercase, hasLowercase, hasDigit, hasSpecialChar).count { it } >= 3
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
