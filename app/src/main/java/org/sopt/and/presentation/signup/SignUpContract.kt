package org.sopt.and.presentation.signup

import org.sopt.and.domain.model.User
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiState

class SignUpContract {

    data class State(
        val user: User = User(name = "", password = "", hobby = ""),
        val showPassword: Boolean = false,
    ) : UiState {
        val signUpButtonEnabled: Boolean
            get() = user.isNotBlank()
    }

    sealed class Event : UiEvent {
        data class OnUserNameChanged(val name: String) : Event()
        data class OnUserPasswordChanged(val password: String) : Event()
        data class OnUserHobbyChanged(val hobby: String) : Event()
        data object TogglePasswordVisibility : Event()
        data object OnSignUpClick : Event()
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToSignIn : SideEffect
        data class ShowSnackBar(val message: String) : SideEffect
    }

}
