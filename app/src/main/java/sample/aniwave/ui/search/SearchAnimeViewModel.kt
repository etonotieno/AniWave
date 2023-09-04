package sample.aniwave.ui.search

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sample.aniwave.data.FileUtils
import sample.aniwave.data.model.Anime
import sample.aniwave.data.repository.AnimeRepository
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState>
        get() = _uiState

    fun searchAnime(resolver: ContentResolver, uri: Uri, file: File) {
        viewModelScope.launch {
            _uiState.emit(SearchUiState.Loading)
            try {
                FileUtils.copyStreamToFile(resolver.openInputStream(uri), file)

                val anime = animeRepository.searchAnime(file)
                if (anime != null) {
                    _uiState.emit(SearchUiState.Success(anime))
                } else {
                    _uiState.emit(SearchUiState.Error("Unable to find a matching Anime"))
                }
            } catch (t: Throwable) {
                _uiState.emit(SearchUiState.Error())
            }
        }
    }
}

sealed interface SearchUiState {
    data object Initial : SearchUiState
    data object Loading : SearchUiState
    data class Error(val message: String? = null) : SearchUiState
    data class Success(val anime: Anime) : SearchUiState
}
