package org.sopt.and.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Event : UiEvent, SideEffect : UiSideEffect> :
    ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent: SharedFlow<Event> = _uiEvent.asSharedFlow()

    private val _uiSideEffect = MutableSharedFlow<UiSideEffect>(replay = 1)
    val uiSideEffect: SharedFlow<UiSideEffect> = _uiSideEffect.asSharedFlow()

    val currentState: State
        get() = uiState.value

    fun setEvent(event: Event) = dispatchEvent(event)

    private fun dispatchEvent(event: Event) =
        viewModelScope.launch {
            handleEvent(event)
        }

    protected abstract suspend fun handleEvent(event: Event)

    protected fun setState(reduce: State.() -> State) =
        currentState.reduce().also { newState ->
            _uiState.value = newState
        }

    protected fun setSideEffect(sideEffect: SideEffect) =
        viewModelScope.launch {
            _uiSideEffect.emit(sideEffect)
        }

}
