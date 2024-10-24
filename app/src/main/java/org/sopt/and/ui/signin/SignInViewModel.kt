package org.sopt.and.ui.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.sopt.and.model.UserInfo

class SignInViewModel : ViewModel() {

    private val _userEnteredInfo = mutableStateOf(UserInfo())
    val userEnteredInfo: State<UserInfo> = _userEnteredInfo

    private val _snackbarMessage = mutableStateOf("")
    val snackbarMessage: State<String> = _snackbarMessage

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    fun onEmailChanged(newEmail: String) {
        _userEnteredInfo.value = userEnteredInfo.value.copy(email = newEmail)
    }

    fun onPasswordChanged(newPassword: String) {
        _userEnteredInfo.value = userEnteredInfo.value.copy(password = newPassword)
    }

    fun validateSignInCredentials(userInfo: UserInfo, onLoginSuccess: () -> Unit) {
        if (userInfo.email.isNotEmpty() && userInfo.password.isNotEmpty()) {
            when {
                _userEnteredInfo.value.email != userInfo.email -> {
                    _snackbarMessage.value = "이메일이 일치하지 않습니다."
                }

                _userEnteredInfo.value.password != userInfo.password -> {
                    _snackbarMessage.value = "비밀번호가 일치하지 않습니다."
                }

                else -> {
                    onLoginSuccess()
                }
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

    fun togglePasswordVisibility() {
        _showPassword.value = !showPassword.value
    }
}
