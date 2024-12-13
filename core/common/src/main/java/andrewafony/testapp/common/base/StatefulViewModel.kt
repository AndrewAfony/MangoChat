package andrewafony.testapp.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StatefulViewModel<UiState, UiEvent>(
    private val initialState: UiState
): ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _state = MutableStateFlow<UiState>(initialState)
    val state = _state.asStateFlow()

    fun sendEvent(event: UiEvent) {
        viewModelScope.launch { _events.emit(event) }
    }

    fun updateState(reducer: UiState.() -> UiState) {
        _state.update(reducer)
    }
}