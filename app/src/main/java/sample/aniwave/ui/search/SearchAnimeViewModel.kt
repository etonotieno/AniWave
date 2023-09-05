package sample.aniwave.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import sample.aniwave.data.repository.AnimeRepository
import sample.aniwave.data.source.network.convertErrorBody
import sample.aniwave.data.source.network.model.AnimeApiError
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState>
        get() = _uiState

    fun searchAnime(writeToFile: () -> Unit, file: File) {
        viewModelScope.launch {
            _uiState.emit(SearchUiState.Loading)
            try {
                writeToFile()

                val anime = animeRepository.searchAnime(file)
                if (anime != null) {
                    _uiState.emit(SearchUiState.Success(anime))
                } else {
                    _uiState.emit(SearchUiState.Error(ERROR_NO_MATCH))
                }
            } catch (e: HttpException) {
                Log.e(TAG, "SearchAnime HttpException", e)

                val error = convertErrorBody<AnimeApiError>(e)
                val message = error?.message ?: ERROR_MESSAGE
                _uiState.emit(SearchUiState.Error(message))
            } catch (e: IOException) {
                Log.e(TAG, "SearchAnime IOException", e)
                _uiState.emit(SearchUiState.Error())
            }
        }
    }

    companion object {
        private const val TAG = "SearchAnimeViewModel"
        private const val ERROR_MESSAGE = "An error occurred while loading anime"
        private const val ERROR_NO_MATCH = "Unable to find a matching Anime"
    }
}
