package sample.aniwave.ui.home

import sample.aniwave.data.model.Anime

sealed interface HomeFeedUiState {
    data object Loading : HomeFeedUiState
    data class Error(val message: String? = null) : HomeFeedUiState
    data class Success(val anime: List<Anime>) : HomeFeedUiState
}
