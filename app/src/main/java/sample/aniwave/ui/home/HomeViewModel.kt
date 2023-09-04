package sample.aniwave.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sample.aniwave.data.model.Anime
import sample.aniwave.data.repository.AnimeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    init {
        refresh()
    }

    val uiState = animeRepository.observeAll()
        .map<List<Anime>, HomeFeedUiState> {
            HomeFeedUiState.Success(it)
        }
        .onStart { emit(HomeFeedUiState.Loading) }
        .catch { emit(HomeFeedUiState.Error()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeFeedUiState.Loading,
        )

    fun refresh() {
        viewModelScope.launch {
            animeRepository.refresh()
            _isRefreshing.emit(false)
        }
    }
}
