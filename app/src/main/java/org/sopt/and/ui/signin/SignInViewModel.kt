package org.sopt.and.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var snackbarMessage by mutableStateOf("")
        private set

    var showPassword by mutableStateOf(false)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    fun onNameChanged(newName: String) {
        name = newName
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun clearSnackbarMessage() {
        snackbarMessage = ""
    }

    fun togglePasswordVisibility() {
        showPassword = !showPassword
    }

    fun login() = viewModelScope.launch {
        val result = loginUseCase(name, password)

        result.onSuccess {
            snackbarMessage = "로그인 성공"
            isLoggedIn = true
        }.onFailure {
            snackbarMessage = "로그인 실패: ${it.message}"
        }
    }

}
