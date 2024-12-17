package org.sopt.and.presentation.signin

import org.sopt.and.domain.model.Login
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiState

class SignInContract {

    data class State(
        val login: Login = Login(name = "", password = ""),
        val showPassword: Boolean = false,
    ) : UiState

    sealed class Event : UiEvent {
        data class OnUserNameChanged(val name: String) : Event()
        data class OnUserPasswordChanged(val password: String) : Event()
        data object TogglePasswordVisibility : Event()
        data object OnSignInClick : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToHome : SideEffect
        data class ShowSnackBar(val message: String) : SideEffect
    }

}
