package org.sopt.and.ui.my

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.FetchMyHobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val fetchMyHobbyUseCase: FetchMyHobbyUseCase
) : ViewModel() {

    var hobby by mutableStateOf("")
        private set


    fun fetchUserHobby() = viewModelScope.launch {
        val result = fetchMyHobbyUseCase()

        result.onSuccess {
            hobby = it
        }.onFailure { error ->
            //TODO
        }
    }
}
