package org.sopt.and.presentation.signin

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.Login
import org.sopt.and.domain.usecase.LoginUseCase
import org.sopt.and.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<SignInContract.State, SignInContract.Event, SignInContract.SideEffect>() {

    override fun createInitialState(): SignInContract.State = SignInContract.State()

    override suspend fun handleEvent(event: SignInContract.Event) {
        when (event) {
            is SignInContract.Event.OnUserNameChanged -> updateLogin { copy(name = event.name) }
            is SignInContract.Event.OnUserPasswordChanged -> updateLogin { copy(password = event.password) }
            is SignInContract.Event.TogglePasswordVisibility -> togglePasswordVisibility()
            is SignInContract.Event.OnSignInClick -> signIn()
        }
    }

    private fun updateLogin(update: Login.() -> Login) =
        setState { copy(login = login.update()) }

    private fun togglePasswordVisibility() =
        setState { copy(showPassword = !showPassword) }

    private fun signIn() =
        viewModelScope.launch {
            val currentLogin = currentState.login
            val result = loginUseCase(currentLogin)

            result
                .onSuccess {
                    setSideEffect(SignInContract.SideEffect.NavigateToHome)
                }
                .onFailure {
                    setSideEffect(SignInContract.SideEffect.ShowSnackBar("로그인 실패: ${it.message}"))
                }
        }

}
