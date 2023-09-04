package sample.aniwave.ui.home

import android.util.Log
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
import retrofit2.HttpException
import sample.aniwave.data.model.Anime
import sample.aniwave.data.repository.AnimeRepository
import sample.aniwave.data.source.network.convertErrorBody
import sample.aniwave.data.source.network.model.AnimeApiError
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
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
        .catch {
            Log.e(TAG, "HomeFeedUiState", it)
            emit(HomeFeedUiState.Error())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeFeedUiState.Loading,
        )

    fun refresh() {
        viewModelScope.launch {
            try {
                animeRepository.refresh()
            } catch (e: HttpException) {
                val error = convertErrorBody<AnimeApiError>(e)
                Log.e(TAG, "AnimeApi HttpException.\n$error", e)
            } catch (e: IOException) {
                Log.e(TAG, "AnimeApi IOException", e)
            }
            _isRefreshing.emit(false)
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
