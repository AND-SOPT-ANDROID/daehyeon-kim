package org.sopt.and.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.RegisterUserUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var showPassword by mutableStateOf(false)
        private set

    var hobby by mutableStateOf("")
        private set

    var snackbarMessage by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var shouldNavigateToSignIn by mutableStateOf(false)
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

    fun clearSnackbarMessage() {
        snackbarMessage = ""
    }

    fun togglePasswordVisibility() {
        showPassword = !showPassword
    }

}
