package sample.aniwave.data.repository

import kotlinx.coroutines.flow.Flow
import sample.aniwave.data.model.Anime

interface AnimeRepository {

    fun observeAll(): Flow<List<Anime>>

    suspend fun refresh()
}
