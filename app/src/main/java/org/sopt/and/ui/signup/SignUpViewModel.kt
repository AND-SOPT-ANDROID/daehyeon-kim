package org.sopt.and.ui.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.domain.util.error.RegisterError
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    var showPassword by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set

    var hobby by mutableStateOf("")
        private set

    var snackbarMessage by mutableStateOf("")
        private set

    fun onUserNameChanged(newUserName: String) {
        userName = newUserName
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun onHobbyChanged(newHobby: String) {
        hobby = newHobby
    }

    var isLoading by mutableStateOf(false)
        private set

    var shouldNavigateToSignIn by mutableStateOf(false)
        private set

    fun onSignUpComplete() {
        shouldNavigateToSignIn = false
    }

    fun isSignUpButtonEnabled(): Boolean =
        !isLoading && userName.isNotBlank() && password.isNotBlank() && hobby.isNotBlank()


    fun signUp() = viewModelScope.launch {
        isLoading = true
        val result = registerUserUseCase(
            userName = userName,
            password = password,
            hobby = hobby
        )

        try {
            result
                .onSuccess {
                    snackbarMessage = "회원가입이 완료되었습니다."
                    shouldNavigateToSignIn = true
                }
                .onFailure {
                    snackbarMessage = "회원가입 실패: ${it.message}"
                    shouldNavigateToSignIn = false
                }
        } finally {
            isLoading = false
        }
    }


    private fun handleFailure(error: Throwable) {
        Log.d("asd", "asd")
        val errorMessage = when (error) {
            is RegisterError.InvalidRequest -> RegisterError.InvalidRequest("요청 본문이 유효하지 않습니다.").message
            is RegisterError.InvalidPath -> RegisterError.InvalidRequest("유효하지 않은 경로로 요청이 들어왔습니다. 경로와 메소드를 확인하세요.").message
            is RegisterError.InvalidLength -> RegisterError.InvalidLength("userName, password, hobby는 8자를 초과할 수 없습니다.").message
            is RegisterError.DuplicateUserName -> RegisterError.DuplicateUserName("이미 존재하는 사용자 이름입니다.").message
            else -> RegisterError.UnknownError("알 수 없는 오료가 발생햇습니다.").message
        }
        snackbarMessage = errorMessage
    }

    fun clearSnackbarMessage() {
        snackbarMessage = ""
    }

    fun togglePasswordVisibility() {
        showPassword = !showPassword
    }

}
