package org.sopt.and.presentation.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.User
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : BaseViewModel<SignUpContract.State, SignUpContract.Event, SignUpContract.SideEffect>() {

    override fun createInitialState(): SignUpContract.State = SignUpContract.State()

    override suspend fun handleEvent(event: SignUpContract.Event) {
        when (event) {
            is SignUpContract.Event.OnUserNameChanged -> updateUser { copy(name = event.name) }
            is SignUpContract.Event.OnUserPasswordChanged -> updateUser { copy(password = event.password) }
            is SignUpContract.Event.OnUserHobbyChanged -> updateUser { copy(hobby = event.hobby) }
            is SignUpContract.Event.TogglePasswordVisibility -> togglePasswordVisibility()
            is SignUpContract.Event.OnSignUpClick -> requestToSignUp()
        }
    }

    private fun updateUser(update: User.() -> User) =
        setState { copy(user = user.update()) }

    private fun togglePasswordVisibility() =
        setState { copy(showPassword = !showPassword) }

    private fun requestToSignUp() =
        viewModelScope.launch {
            val currentUser = currentState.user
            val result = registerUserUseCase(currentUser)

            result
                .onSuccess {
                    setSideEffect(SignUpContract.SideEffect.NavigateToSignIn)
                }
                .onFailure { error ->
                    setSideEffect(SignUpContract.SideEffect.ShowSnackBar("회원가입 실패: ${error.message}"))
                }
        }
}
