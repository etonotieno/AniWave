package sample.aniwave.ui.search

import sample.aniwave.data.model.Anime

sealed interface SearchUiState {
    data object Initial : SearchUiState
    data object Loading : SearchUiState
    data class Error(val message: String? = null) : SearchUiState
    data class Success(val anime: Anime) : SearchUiState
}
