package sample.aniwave

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val name: String = "Android",
) : ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    init {
        _uiState.update { name }
    }
}
